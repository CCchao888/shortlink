package com.chao.shortlink.admin.remote.dto.resp;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-29
 * Description: 短链接分组数量查询响应DTO
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组下的短链接数量
     */
    private Integer shortLinkCount;

}
