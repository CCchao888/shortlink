package com.chao.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.chao.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
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
     * 更新短链接
     * @param requestParam
     */
    default void updateShortLink(ShortLinkUpdateReqDTO requestParam){
       HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/update",JSON.toJSONString(requestParam));
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

    /**
     * 查询分组下的短链接数量
     * @param
     * @return
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParams){
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("requestParams",requestParams);
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count",requestMap);
        return JSON.parseObject(resultPageStr,new TypeReference<>(){
        });
    }

    /**
     * 根据url获取标题
     * @param url
     * @return
     */
    default Result<String> getTitleByUrl(@RequestParam("url") String url){

        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/title?url=" + url);
        return JSON.parseObject(resultPageStr,new TypeReference<>(){
        });
    }
}
