package com.chao.shortlink.project.common.convention.exception;


import com.chao.shortlink.project.common.convention.errorcode.BaseErrorCode;
import com.chao.shortlink.project.common.convention.errorcode.IErrorCode;

/**
 * Author:chao
 * Date:2024-01-22
 * Description: 远程服务调用异常
 */


public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}