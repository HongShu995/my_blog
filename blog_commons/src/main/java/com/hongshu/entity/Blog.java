package com.hongshu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 博客
 *
 * @author Hong_Shu995
 * @date 2021-12-13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_blog")
public class Blog
{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 作者
     */
    private Integer userId;

    /**
     * 文章分类
     */
    private Integer categoryId;

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
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}
