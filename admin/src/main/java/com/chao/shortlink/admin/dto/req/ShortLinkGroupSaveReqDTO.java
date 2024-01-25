package com.chao.shortlink.admin.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接新增分组请求DTO
 */
@Data
public class ShortLinkGroupSaveReqDTO {

    /**
     * 分组名
     */
    private String name;

}
