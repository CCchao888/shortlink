package com.chao.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * Author:chao
 * Date:2024-01-28
 * Description: 短链接分页查询请求DTO
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;
}
