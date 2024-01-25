package com.chao.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chao.shortlink.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接分组实体
 */
@Data
@TableName("t_group")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GroupDO extends BaseDO {

    //@TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;

}
