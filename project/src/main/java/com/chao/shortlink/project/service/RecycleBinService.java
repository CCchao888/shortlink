package com.chao.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.shortlink.project.dao.entity.ShortLinkDO;
import com.chao.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.chao.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: 回收站管理接口层
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 移至回收站
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询短链接
     *
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

}