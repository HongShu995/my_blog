package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO
{

    /**
     * id
     */
    private Integer id;

    /**
     * 文章缩略图
     */
    private String blogCover;

    /**
     * 标题
     */
    private String blogTitle;

    /**
     * 内容
     */
    private String blogContent;

    /**
     * 点赞量
     */
    private Integer likeCount;

    /**
     * 浏览量
     */
    private Integer viewsCount;

    /**
     * 文章类型
     */
    private Integer type;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 原文链接
     */
    private String originalUrl;

    /**
     * 发表时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 文章分类id
     */
    private Integer categoryId;

    /**
     * 文章分类名
     */
    private String categoryName;

    /**
     * 文章标签
     */
    private List<TagDTO> tagDTOList;

    /**
     * 上一篇文章
     */
    private BlogPaginationDTO lastBlog;

    /**
     * 下一篇文章
     */
    private BlogPaginationDTO nextBlog;

    /**
     * 推荐文章列表
     */
    private List<BlogRecommendDTO> recommendBlogList;

    /**
     * 最新文章列表
     */
    private List<BlogRecommendDTO> newestBlogList;

}
