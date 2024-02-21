package com.chao.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.admin.common.convention.exception.ClientException;
import com.chao.shortlink.admin.common.enums.UserErrorCodeEnums;
import com.chao.shortlink.admin.dao.entity.UserDO;
import com.chao.shortlink.admin.dao.mapper.UserMapper;
import com.chao.shortlink.admin.dto.req.UserLoginReqDTO;
import com.chao.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.chao.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.chao.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.chao.shortlink.admin.dto.resp.UserRespDTO;
import com.chao.shortlink.admin.service.GroupService;
import com.chao.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    private final StringRedisTemplate stringRedisTemplate;

    private final GroupService groupService;

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
                groupService.saveGroup(userRegisterReqDTO.getUsername(),"默认分组");
            }else {
                throw new ClientException(UserErrorCodeEnums.USER_NAME_EXIST);
            }
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void update(UserUpdateReqDTO userUpdateReqDTO) {
        // TODO 验证当前用户名是否为登录用户，防止当前用户修改了其他用户的信息

        LambdaUpdateWrapper<UserDO> updateWrappers = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, userUpdateReqDTO.getUsername());
        baseMapper.update(BeanUtil.toBean(userUpdateReqDTO, UserDO.class),updateWrappers);

    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException("用户不存在");
        }

        Map<Object ,Object> hasLoginMap = stringRedisTemplate.opsForHash().entries("login_" + userLoginReqDTO.getUsername());
        if (CollUtil.isNotEmpty(hasLoginMap)) {
            String token = hasLoginMap.keySet().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new ClientException("用户登录错误"));
            return new UserLoginRespDTO(token);
        }
        String uuid = UUID.randomUUID().toString();
        // 将token缓存到redis
        stringRedisTemplate.opsForHash().put("login_"+userLoginReqDTO.getUsername(),uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_"+userLoginReqDTO.getUsername(), 20, TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get("login_"+username,token) != null;
    }

    @Override
    public void logOut(String username, String token) {
        if(checkLogin(username, token)){
            stringRedisTemplate.delete("login_"+username);
        }else {
            throw new ClientException("用户未登录或者用户token不存在");
        }
    }
}
