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
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 分组下短链接数量
     */
    private Integer shortLinkCount;

}
