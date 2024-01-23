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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 用户接口实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

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
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }


    @Override
    public void register(UserRegisterReqDTO userRegisterReqDTO) {
        if(hasUsername(userRegisterReqDTO.getUsername())){
            throw new ClientException(UserErrorCodeEnums.USER_NAME_EXIST);
        }
        int inserted = baseMapper.insert(BeanUtil.toBean(userRegisterReqDTO, UserDO.class));
        if(inserted < 1){
            throw new ClientException(UserErrorCodeEnums.USER_SAVE_ERROR);
        }


    }
}
