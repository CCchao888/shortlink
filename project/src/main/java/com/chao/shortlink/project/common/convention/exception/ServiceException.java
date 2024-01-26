package com.chao.shortlink.project.common.convention.exception;


import com.chao.shortlink.project.common.convention.errorcode.BaseErrorCode;
import com.chao.shortlink.project.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 服务端异常
 */

public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}

