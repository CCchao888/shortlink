package com.chao.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Author:chao
 * Date:2024-01-29
 * Description: 有效期类型
 */
@RequiredArgsConstructor
public enum ValidDateTypeEnum {

    /**
     * 永久有效
     */
    PERMANENT(0),

    /**
     * 自定义有效时间
     */
    CUSTOM(1);

    @Getter
    private final int type;

}
