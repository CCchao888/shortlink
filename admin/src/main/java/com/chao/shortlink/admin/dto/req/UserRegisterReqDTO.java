package com.chao.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-23
 * Description: 用户注册请求DTO
 */
@Data
public class UserRegisterReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

}
