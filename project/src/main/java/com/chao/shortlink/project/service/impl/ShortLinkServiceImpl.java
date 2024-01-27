package com.chao.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chao.shortlink.project.dao.entity.ShortLinkDO;
import com.chao.shortlink.project.dao.mapper.ShortLinkMapper;
import com.chao.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.chao.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.chao.shortlink.project.service.ShortLinkService;
import com.chao.shortlink.project.toolkit.HashUtil;
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

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO shortLinkCreateReqDTO) {
        String shortLinkSuffix = generateSuffix(shortLinkCreateReqDTO);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(shortLinkCreateReqDTO, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setFullShortUrl(shortLinkCreateReqDTO.getDomain() + "/" +shortLinkSuffix);
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .gid(shortLinkCreateReqDTO.getGid())
                .originUrl(shortLinkCreateReqDTO.getOriginUrl())
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .build();
    }
    private String generateSuffix(ShortLinkCreateReqDTO shortLinkCreateReqDTO){
        return HashUtil.hashToBase62(shortLinkCreateReqDTO.getOriginUrl());
    }
}
