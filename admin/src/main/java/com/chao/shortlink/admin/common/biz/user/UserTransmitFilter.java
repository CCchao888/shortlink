package com.chao.shortlink.admin.common.biz.user;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.chao.shortlink.admin.common.convention.exception.ClientException;
import com.chao.shortlink.admin.common.convention.result.Results;
import com.google.common.collect.Lists;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import static com.chao.shortlink.admin.common.constant.RedisCacheConstant.USER_LOGIN_KEY;
import static com.chao.shortlink.admin.common.enums.UserErrorCodeEnums.USER_TOKEN_FAIL;

/**
 * Author:chao
 * Date:2024-01-26
 * Description: 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {

    private final StringRedisTemplate stringRedisTemplate;

    private final static List<String> IGNORE_URI = Lists.newArrayList(
            "/api/short-link/admin/v1/user/login",
            "/api/short-link/admin/v1/user/has-username"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if(!IGNORE_URI.contains(requestURI)){
            String method = httpServletRequest.getMethod();
            if(!(Objects.equals(requestURI,"api/short-link/admin/v1/user") && Objects.equals(method,"POST"))){
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if(!StrUtil.isAllNotBlank(username,token)){
                    returnJson((HttpServletResponse) servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;

                }
                Object userInfoStr ;
                try{
                    userInfoStr = stringRedisTemplate.opsForHash().get(USER_LOGIN_KEY + username,token);
                    if (userInfoStr == null) {
                        throw new ClientException(USER_TOKEN_FAIL);
                    }
                }catch (Exception ex){
                    returnJson((HttpServletResponse) servletResponse, JSON.toJSONString(Results.failure(new ClientException(USER_TOKEN_FAIL))));
                    return;
                }
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoStr.toString(),UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }

    private void returnJson(HttpServletResponse response, String json) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=uft-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        }
    }

}
