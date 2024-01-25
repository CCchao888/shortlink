package com.chao.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.shortlink.admin.dao.entity.GroupDO;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增分组
     * @param groupName  分组名
     */
    void saveGroup(String groupName);

}
