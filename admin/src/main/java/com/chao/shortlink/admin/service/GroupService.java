package com.chao.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chao.shortlink.admin.dao.entity.GroupDO;
import com.chao.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

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

    /**
     * 查询用户的分组集合
     * @return
     */
    List<ShortLinkGroupRespDTO> listGroup();

}
