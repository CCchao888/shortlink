package com.chao.shortlink.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:chao
 * Date:2024-02-22
 * Description: 网关错误返回信息
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayErrorResult {

    /**
     * HTTP 状态码
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String message;
}
