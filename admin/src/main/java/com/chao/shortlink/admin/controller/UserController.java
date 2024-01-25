package com.chao.shortlink.admin.controller;

import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.common.convention.result.Results;
import com.chao.shortlink.admin.dto.req.UserLoginReqDTO;
import com.chao.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.chao.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.chao.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.chao.shortlink.admin.dto.resp.UserRespDTO;
import com.chao.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Author:chao
 * Date:2024-01-21
 * Description:用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名获取用户信息
     */
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        UserRespDTO result = userService.getUserByUsername(username);
        return Results.success(result);
    }

    /**
     * 判断用户名是否可用
     */
    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 用户注册
     */
    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO){
        userService.register(userRegisterReqDTO);
        return Results.success();
    }

    /**
     * 用户修改个人信息
     */
    @PutMapping("/api/short-link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO userUpdateReqDTO){
        userService.update(userUpdateReqDTO);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/api/short-link/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO){
        UserLoginRespDTO userLoginRespDTO = userService.login(userLoginReqDTO);
        return Results.success(userLoginRespDTO);
    }

    /**
     * 检查用户登录状态
     */
    @GetMapping("/api/short-link/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam String username, @RequestParam String token){
        return Results.success(userService.checkLogin(username,token));
    }

}
