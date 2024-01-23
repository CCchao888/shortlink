package com.chao.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.admin.common.convention.exception.ClientException;
import com.chao.shortlink.admin.common.enums.UserErrorCodeEnums;
import com.chao.shortlink.admin.dao.entity.UserDO;
import com.chao.shortlink.admin.dao.mapper.UserMapper;
import com.chao.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.chao.shortlink.admin.dto.resp.UserRespDTO;
import com.chao.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.chao.shortlink.admin.common.constant.RedisCacheConstant.LOCK_REGISTER_KEY;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    private final RedissonClient redissonClient;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null){
            throw new ClientException(UserErrorCodeEnums.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }


    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        if(!hasUsername(userRegisterReqDTO.getUsername())){
            throw new ClientException(UserErrorCodeEnums.USER_NAME_EXIST);
        }
        // 通过 redisson 的分布式锁，锁定用户名进行串行执行
        RLock lock = redissonClient.getLock(LOCK_REGISTER_KEY+userRegisterReqDTO.getUsername());
        try{
            if(lock.tryLock()){
                // 保存用户记录
                int inserted = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
                if(inserted < 1){
                    throw new ClientException(UserErrorCodeEnums.USER_SAVE_ERROR);
                }
                // 添加用户名到缓存中的布隆过滤器
                userRegisterCachePenetrationBloomFilter.add(userRegisterReqDTO.getUsername());
            }else {
                throw new ClientException(UserErrorCodeEnums.USER_NAME_EXIST);
            }
        }finally {
            lock.unlock();
        }
    }
}
