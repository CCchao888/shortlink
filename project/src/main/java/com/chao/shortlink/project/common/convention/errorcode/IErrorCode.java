package com.chao.shortlink.project.common.convention.errorcode;

/**
 * Author:chao
 * Date:2024-01-22
 * Description:平台错误码
 */

public interface IErrorCode {

    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}