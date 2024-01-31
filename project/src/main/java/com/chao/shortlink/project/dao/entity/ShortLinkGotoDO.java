package com.chao.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:chao
 * Date:2024-01-30
 * Description: 短链接跳转实体
 */
@TableName(value ="t_link_goto")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkGotoDO {

    /**
     * ID
     */
    private Long id;

    /**
     * 短链接分组标识
     */
    private String gid;

    /**
     * 短链接完整的短链接
     */
    private String fullShortUrl;

}
