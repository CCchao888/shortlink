package com.chao.shortlink.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:chao
 * Date:2024-01-21
 * Description:用户管理控制层
 */
@RestController
public class UserController {

    /**
     * 根据用户名获取用户信息
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public String getUserByUsername(@PathVariable("username") String username) {
        return "Hi" + username;
    }
}
