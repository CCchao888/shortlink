package com.chao.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 用户登录请求DTO
 */
@Data
public class UserLoginReqDTO {

    /**
     *  用户名
     */
    private String username;

    /**
     *  密码
     */
    private String password;

}
