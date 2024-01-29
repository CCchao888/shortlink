package com.chao.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:chao
 * Date:2024-01-29
 * Description: 短链接中台远程调用服务
 */
public interface ShortLinkRemoteService {

    /**
     * 创建短链接
     * @param shortLinkCreateReqDTO
     * @return
     */
    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO){
        String resultStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create",JSON.toJSONString(shortLinkCreateReqDTO));
        return JSON.parseObject(resultStr,new TypeReference<>(){
        });
    }


    /**
     * 分页查询短链接
     * @param shortLinkPageReqDTO
     * @return
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gid",shortLinkPageReqDTO.getGid());
        requestMap.put("current",shortLinkPageReqDTO.getCurrent());
        requestMap.put("size",shortLinkPageReqDTO.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page",requestMap);
        return JSON.parseObject(resultPageStr,new TypeReference<>(){
        });
    }
}
