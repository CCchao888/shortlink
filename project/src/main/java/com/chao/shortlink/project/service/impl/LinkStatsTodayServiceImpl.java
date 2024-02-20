package com.chao.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.project.dao.entity.LinkStatsTodayDO;
import com.chao.shortlink.project.dao.mapper.LinkStatsTodayMapper;
import com.chao.shortlink.project.service.LinkStatsTodayService;
import org.springframework.stereotype.Service;

/**
 * Author:chao
 * Date:2024-02-20
 * Description: 短链接今日统计接口实现层
 */

@Service
public class LinkStatsTodayServiceImpl extends ServiceImpl<LinkStatsTodayMapper, LinkStatsTodayDO> implements LinkStatsTodayService {
}