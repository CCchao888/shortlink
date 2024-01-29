package com.chao.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:chao
 * Date:2024-01-29
 * Description: 短链接后管控制层
 */
@RestController
public class ShortLinkController {

    /**
     * 后续重构为Spring cloud的feign调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     * @param shortLinkCreateReqDTO
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO){
        return shortLinkRemoteService.createShortLink(shortLinkCreateReqDTO);
    }

    /**
     * 分页查询
     * @param shortLinkPageReqDTO
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO){
        return shortLinkRemoteService.pageShortLink(shortLinkPageReqDTO);
    }
}
