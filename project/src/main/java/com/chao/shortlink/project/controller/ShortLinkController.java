package com.chao.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chao.shortlink.project.common.convention.result.Result;
import com.chao.shortlink.project.common.convention.result.Results;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.chao.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author:chao
 * Date:2024-01-26
 * Description: 短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     * @param shortLinkCreateReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO){
         return Results.success(shortLinkService.createShortLink(shortLinkCreateReqDTO));
    }

    /**
     * 分页查询
     * @param shortLinkPageReqDTO
     * @return
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink( ShortLinkPageReqDTO shortLinkPageReqDTO){
        return Results.success(shortLinkService.pageShortLink(shortLinkPageReqDTO));
    }

    /**
     * 查询分组内短链接数量
     * @param requestParams
     * @return
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParams") List<String> requestParams){
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParams));
    }



}
