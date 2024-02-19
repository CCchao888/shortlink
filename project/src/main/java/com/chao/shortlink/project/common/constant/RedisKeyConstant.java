package com.chao.shortlink.project.common.constant;

/**
 * Author:chao
 * Date:2024-01-31
 * Description:
 */
public class RedisKeyConstant {

    /**
     * 短链跳转前缀 key
     */
    public static final String GOTO_SHORT_LINK_KEY = "short-link_goto_%s";

    /**
     * 短链空值跳转前缀 key
     */
    public static final String GOTO_IS_NULL_SHORT_LINK_KEY = "short-link_is-null_goto_%s";

    /**
     * 短链跳转锁前缀 key
     */
    public static final String LOCK_GOTO_SHORT_LINK_KEY = "short-link_lock_goto_%s";
}
