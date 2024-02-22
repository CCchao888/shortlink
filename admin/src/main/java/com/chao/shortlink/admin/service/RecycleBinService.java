package com.chao.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: URL 回收站接口层
 */


public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}