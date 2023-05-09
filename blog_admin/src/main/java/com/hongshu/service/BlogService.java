package com.hongshu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hongshu.dto.*;
import com.hongshu.entity.Blog;
import com.hongshu.vo.*;

import java.util.List;

/**
 * 博客文章服务
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
public interface BlogService extends IService<Blog>
{

    /**
     * 查询文章归档
     *
     * @return 文章归档
     */
    PageResult<ArchiveDTO> listArchives();

    /**
     * 查询后台文章
     *
     * @param condition 条件
     * @return 文章列表
     */
    PageResult<BlogBackDTO> listBlogBacks(ConditionVO condition);

    /**
     * 查询首页文章
     *
     * @return 文章列表
     */
    List<BlogHomeDTO> listBlogs(ConditionVO condition);

    /**
     * 根据条件查询文章列表
     *
     * @param condition 条件
     * @return 文章列表
     */
    BlogPreviewListDTO listBlogsByCondition(ConditionVO condition);


    /**
     * 根据id查看后台文章
     *
     * @param blogId 文章id
     * @return 文章列表
     */
    BlogVO getBlogBackById(Integer blogId);

    /**
     * 根据id查看文章
     *
     * @param blogId 文章id
     * @return {@link BlogDTO} 文章信息
     */
    BlogDTO getBlogById(Integer blogId);

    /**
     * 点赞文章
     *
     * @param blogId 文章id
     */
    void saveBlogLike(Integer blogId);

    /**
     * 添加或修改文章
     *
     * @param blogVO 文章信息
     */
    void saveOrUpdateBlog(BlogVO blogVO);

    /**
     * 修改文章置顶
     *
     * @param blogTopVO 文章置顶信息
     */
    void updateBlogTop(BlogTopVO blogTopVO);

    /**
     * 删除或恢复文章
     *
     * @param deleteVO 逻辑删除对象
     */
    void updateBlogDelete(DeleteVO deleteVO);

    /**
     * 物理删除文章
     *
     * @param blogIdList 文章id集合
     */
    void deleteBlogs(List<Integer> blogIdList);
}
