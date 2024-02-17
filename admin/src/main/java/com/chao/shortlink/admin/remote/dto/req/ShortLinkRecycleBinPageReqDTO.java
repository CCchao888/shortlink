package com.chao.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: 回收站短链接分页请求参数
 */


@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {

    /**
     * 分组标识
     */
    private List<String> gidList;
}