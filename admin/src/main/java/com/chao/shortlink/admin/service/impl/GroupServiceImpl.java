package com.chao.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.admin.dao.entity.GroupDO;
import com.chao.shortlink.admin.dao.mapper.GroupMapper;
import com.chao.shortlink.admin.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
}
