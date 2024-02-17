package com.chao.shortlink.project.service;

/**
 * Author:chao
 * Date:2024-02-17
 * Description: URL标题接口层
 */
public interface UrlTitleService {

    /**
     *  根据URL获取标题
     * @param url
     * @return
     */
    String getTitleByUrl(String url);
}
