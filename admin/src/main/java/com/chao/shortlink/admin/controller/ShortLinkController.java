package com.chao.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.common.convention.result.Results;
import com.chao.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkBatchCreateReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkBaseInfoRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkBatchCreateRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.chao.shortlink.admin.toolkit.EasyExcelWebUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author:chao
 * Date:2024-01-29
 * Description: 短链接后管控制层
 */
@RestController(value = "shortLinkControllerByAdmin")
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    /**
     * 创建短链接
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return shortLinkActualRemoteService.createShortLink(requestParam);
    }

    /**
     * 更新短链接
     * @param requestParam
     * @return
     */
    @PutMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkActualRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 分页查询
     * @param requestParam
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkActualRemoteService.pageShortLink(requestParam.getGid(), requestParam.getOrderTag(), requestParam.getCurrent(), requestParam.getSize());
    }

    /**
     * 批量创建短链接
     */
    @SneakyThrows
    @PostMapping("/api/short-link/admin/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "批量创建短链接-SaaS短链接系统", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }


}
