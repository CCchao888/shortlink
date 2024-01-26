package com.chao.shortlink.project.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-27
 * Description: 短链接创建链接请求DTO
 */
@Data
public class ShortLinkCreateReqDTO {

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
