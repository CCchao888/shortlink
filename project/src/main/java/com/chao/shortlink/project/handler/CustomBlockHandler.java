package com.chao.shortlink.project.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chao.shortlink.project.common.convention.result.Result;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * Author:chao
 * Date:2024-02-21
 * Description: 自定义流控策略
 */


public class CustomBlockHandler {

    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
