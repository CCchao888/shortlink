package com.chao.shortlink.admin.controller;


import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.remote.dto.ShortLinkRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: URL标题控制层
 */
@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    /**
     * 后续重构为Spring cloud的feign调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 根据URL获取标题
     * @param url
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url){
        return shortLinkRemoteService.getTitleByUrl(url);
    }

}
