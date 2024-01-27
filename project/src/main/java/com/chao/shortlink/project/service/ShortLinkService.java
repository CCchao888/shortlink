package com.chao.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.shortlink.project.dao.entity.ShortLinkDO;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * Author:chao
 * Date:2024-01-26
 * Description: 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param shortLinkCreateReqDTO
     * @return
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO);

}
