package com.hongshu.service;

import com.hongshu.dto.BlogBackInfoDTO;
import com.hongshu.dto.BlogHomeInfoDTO;
import com.hongshu.vo.BlogInfoVO;
import com.hongshu.vo.WebsiteConfigVO;

/**
 * 博客信息服务类
 *
 * @author Hong_Shu995
 * @date 2021-12-13
 */
public interface BlogInfoService
{
    /**
     * 获取首页数据
     *
     * @return 博客首页信息
     */
    BlogHomeInfoDTO getBlogHomeInfo();

    /**
     * 获取后台首页数据
     *
     * @return 博客后台信息
     */
    BlogBackInfoDTO getBlogBackInfo();

    /**
     * 获取网站配置
     *
     * @return {@link WebsiteConfigVO} 网站配置
     */
    WebsiteConfigVO getWebsiteConfig();

    /**
     * 保存或更新网站配置
     *
     * @param websiteConfigVO 网站配置
     */
    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    /**
     * 获取关于我内容
     *
     * @return 关于我内容
     */
    String getAbout();

    /**
     * 修改关于我内容
     *
     * @param blogInfoVO 博客信息
     */
    void updateAbout(BlogInfoVO blogInfoVO);

    /**
     * 获取后台登录背景
     *
     * @return 背景地址
     */
    String getAdminCover();

}
