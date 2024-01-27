package com.chao.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:chao
 * Date:2024-01-27
 * Description: 创建短链接响应DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortLinkCreateRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;
}
