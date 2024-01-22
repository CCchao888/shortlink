package com.chao.shortlink.admin.common.convention.exception;

import com.chao.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.chao.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 客户端异常
 */

public class ClientException extends AbstractException {

    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}

