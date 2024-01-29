package com.chao.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-28
 * Description: 短链接分页查询请求DTO
 */
@Data
public class ShortLinkPageReqDTO extends Page   {

    /**
     * 分组标识
     */
    private String gid;
}
