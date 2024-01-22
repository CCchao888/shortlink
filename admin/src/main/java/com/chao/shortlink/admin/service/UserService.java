package com.chao.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.shortlink.admin.dao.entity.UserDO;
import com.chao.shortlink.admin.dto.resp.UserRespDTO;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

}
