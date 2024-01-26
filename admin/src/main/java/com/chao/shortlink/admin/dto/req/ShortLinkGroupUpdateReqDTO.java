package com.chao.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-26
 * Description: 短链接修改分组请求DTO
 */
@Data
public class ShortLinkGroupUpdateReqDTO {

    /**
     * 分组名
     */
    private String name;

    /**
     * 分组标识
     */
    private String gid;

}
