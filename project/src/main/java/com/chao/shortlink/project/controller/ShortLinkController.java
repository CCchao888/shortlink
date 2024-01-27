package com.chao.shortlink.project.controller;

import com.chao.shortlink.project.common.convention.result.Result;
import com.chao.shortlink.project.common.convention.result.Results;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



}
