package com.chao.shortlink.project.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chao.shortlink.project.common.convention.result.Result;
import com.chao.shortlink.project.common.convention.result.Results;
import com.chao.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.chao.shortlink.project.handler.CustomBlockHandler;
import com.chao.shortlink.project.service.ShortLinkService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
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
     * 短链接跳转
     * @param shortUri
     * @param request
     * @param response
     */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response){
        shortLinkService.restoreUrl(shortUri, request, response);
    }


    /**
     * 创建短链接
     * @param shortLinkCreateReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/create")
    @SentinelResource(
            value = "create_short-link",
            blockHandler = "createShortLinkBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO shortLinkCreateReqDTO){
         return Results.success(shortLinkService.createShortLink(shortLinkCreateReqDTO));
    }

    /**
     * 更新短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
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


    /**
     * 批量创建短链接
     */
    @PostMapping("/api/short-link/v1/create/batch")
    public Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam) {
        return Results.success(shortLinkService.batchCreateShortLink(requestParam));
    }


}
