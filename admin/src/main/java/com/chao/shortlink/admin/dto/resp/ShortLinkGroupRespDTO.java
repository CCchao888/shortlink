package com.chao.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接查询分组响应DTO
 */
@Data
public class ShortLinkGroupRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;

}
