package com.hongshu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hongshu.dao.*;
import com.hongshu.dto.BlogBackInfoDTO;
import com.hongshu.dto.BlogHomeInfoDTO;
import com.hongshu.dto.CategoryDTO;
import com.hongshu.dto.TagDTO;
import com.hongshu.entity.About;
import com.hongshu.entity.Blog;
import com.hongshu.entity.WebsiteConfig;
import com.hongshu.service.BlogInfoService;
import com.hongshu.service.PageService;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.vo.BlogInfoVO;
import com.hongshu.vo.PageVO;
import com.hongshu.vo.WebsiteConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hongshu.constant.CommonConstant.*;
import static com.hongshu.enums.BlogStatusEnum.PUBLIC;

 /* 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-13
 */
@Service
public class BlogInfoServiceImpl implements BlogInfoService
{
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private WebsiteConfigDao websiteConfigDao;

    @Autowired
    private AboutDao aboutDao;

    @Autowired
    private PageService pageService;

    @Override
    public BlogHomeInfoDTO getBlogHomeInfo()
    {
        // 查询文章数量
        Long blogCount = blogDao.selectCount(new LambdaQueryWrapper<Blog>()
                .eq(Blog::getStatus, PUBLIC.getStatus())
                .eq(Blog::getIsDelete, FALSE));
        // 查询分类数量
        Long categoryCount = categoryDao.selectCount(null);
        // 查询标签数量
        Long tagCount = tagDao.selectCount(null);
        // 查询访问量
        //Object count = redisService.get(BLOG_VIEWS_COUNT);
        //String viewsCount = Optional.ofNullable(count).orElse(0).toString();
        // 查询网站配置
        WebsiteConfigVO websiteConfig = this.getWebsiteConfig();
        // 查询页面图片
        List<PageVO> pageVOList = pageService.listPages();
        // 封装数据
        return BlogHomeInfoDTO.builder()
                .blogCount(blogCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .viewsCount("0")
                .websiteConfig(websiteConfig)
                .pageList(pageVOList)
                .build();
    }

    @Override
    public BlogBackInfoDTO getBlogBackInfo()
    {
        // 查询用户量
        Long userCount = userInfoDao.selectCount(null);
        // 查询博客文章量
        Long blogCount = blogDao.selectCount(new LambdaQueryWrapper<Blog>()
                .eq(Blog::getStatus, PUBLIC.getStatus())
                .eq(Blog::getIsDelete, FALSE));
        // 查询分类数量
        Long categoryCount = categoryDao.selectCount(null);
        // 查询标签数量
        Long tagCount = tagDao.selectCount(null);
        // 查询分类数据
        List<CategoryDTO> categoryDTOList = categoryDao.listCategoryDTO();
        // 查询标签数据
        List<TagDTO> tagDTOList = BeanCopyUtils.copyList(tagDao.selectList(null), TagDTO.class);

        return BlogBackInfoDTO.builder()
                .userCount(userCount)
                .blogCount(blogCount)
                .categoriseCount(categoryCount)
                .tagsCount(tagCount)
                .categoryDTOList(categoryDTOList)
                .tagDTOList(tagDTOList)
                .build();
    }

    @Override
    public WebsiteConfigVO getWebsiteConfig()
    {
        WebsiteConfigVO websiteConfigVO;
        // 从数据库中加载
        String config = websiteConfigDao.selectById(1).getConfig();
        websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
        return websiteConfigVO;
    }

    @Override
    public void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO)
    {
        // 修改网站配置
        WebsiteConfig websiteConfig = WebsiteConfig.builder()
                .id(1)
                .config(JSON.toJSONString(websiteConfigVO))
                .build();
        websiteConfigDao.updateById(websiteConfig);
    }

    @Override
    public String getAbout()
    {
        return aboutDao.selectById(1).getContent();
    }

    @Override
    public void updateAbout(BlogInfoVO blogInfoVO)
    {
        About about = About.builder()
                .id(1)
                .content(blogInfoVO.getAboutContent())
                .build();
        aboutDao.updateById(about);
    }

    @Override
    public String getAdminCover()
    {
        WebsiteConfigVO websiteConfigVO;
        String config = websiteConfigDao.selectById(1).getConfig();
        websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
        return websiteConfigVO.getAdminCover();
    }
}
