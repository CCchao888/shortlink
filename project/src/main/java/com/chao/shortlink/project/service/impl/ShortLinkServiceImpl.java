package com.chao.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.project.dao.entity.ShortLinkDO;
import com.chao.shortlink.project.dao.mapper.ShortLinkMapper;
import com.chao.shortlink.project.service.ShortLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author:chao
 * Date:2024-01-26
 * Description: 短链接接口实现层
 */
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

}
