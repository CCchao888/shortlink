package com.chao.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.admin.common.biz.user.UserContext;
import com.chao.shortlink.admin.dao.entity.GroupDO;
import com.chao.shortlink.admin.dao.mapper.GroupMapper;
import com.chao.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.chao.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.chao.shortlink.admin.service.GroupService;
import com.chao.shortlink.admin.toolkit.RandomStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {

        String gid;
        do{
            gid = RandomStringUtil.generateRandomString();
        }while (hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .username(UserContext.getUsername())
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);

    }
    private boolean hasGid(String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO groupDO = baseMapper.selectOne(queryWrapper);
        return groupDO != null;
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUsername);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO) {
        LambdaUpdateWrapper<GroupDO> updateWrappers = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getGid, shortLinkGroupUpdateReqDTO.getGid())
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO groupDO = new GroupDO();
        groupDO.setName(shortLinkGroupUpdateReqDTO.getName());
        baseMapper.update(groupDO, updateWrappers);
    }


}
