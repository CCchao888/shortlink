package com.chao.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.shortlink.project.dao.entity.ShortLinkDO;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

/**
 * Author:chao
 * Date:2024-01-26
 * Description: 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param shortLinkCreateReqDTO
     * @return 创建短链接返回响应DTO
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO);

    /**
     * 分页查询短链接
     * @param shortLinkPageReqDTO
     * @return 短链接分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO shortLinkPageReqDTO);

    /**
     * 分组查询短链接数量
     * @param requestParams
     * @return 短链接分组返回结果
     */
    List<ShortLinkGroupCountQueryRespDTO>  listGroupShortLinkCount(List<String> requestParams);

}
