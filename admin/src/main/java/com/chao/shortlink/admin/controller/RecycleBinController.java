package com.chao.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.common.convention.result.Results;
import com.chao.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import com.chao.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: 回收站管理控制层
 */

@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 保存回收站
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        shortLinkRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }
    /**
     * 分页查询回收站短链接
     */
    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageRecycleBinShortLink(requestParam);
    }
}
