package com.chao.shortlink.admin.common.constant;

/**
 * Author:chao
 * Date:2024-01-23
 * Description: 短链接 后管 Redis缓存常量类
 */
public class RedisCacheConstant {

    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_REGISTER_KEY = "short-link:lock_user-register:";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";

}
