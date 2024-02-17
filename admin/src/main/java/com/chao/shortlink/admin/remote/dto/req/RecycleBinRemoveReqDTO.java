package com.chao.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: 回收站移除请求参数
 */

@Data
public class RecycleBinRemoveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}