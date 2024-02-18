package com.chao.shortlink.project.service;

import com.chao.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * Author:chao
 * Date:2024-02-18
 * Description: 短链接监控接口层
 */

public interface ShortLinkStatsService {

    /**
     * 获取单个短链接监控数据
     *
     * @param requestParam 获取短链接监控数据入参
     * @return 短链接监控数据
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);
}
