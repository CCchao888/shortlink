package com.chao.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * Author:chao
 * Date:2024-02-18
 * Description: 短链接监控请求参数
 */

@Data
public class ShortLinkStatsReqDTO {

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;
}
