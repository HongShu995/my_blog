package com.hongshu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 文章VO
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "博客文章")
public class BlogVO
{

    /**
     * 文章id
     */
    @ApiModelProperty(name = "id", value = "文章id", dataType = "Integer")
    private Integer id;

    /**
     * 标题
     */
    @NotBlank(message = "文章标题不能为空")
    @ApiModelProperty(name = "blogTitle", value = "文章标题", required = true, dataType = "String")
    private String blogTitle;

    /**
     * 内容
     */
    @NotBlank(message = "文章内容不能为空")
    @ApiModelProperty(name = "blogContent", value = "文章内容", required = true, dataType = "String")
    private String blogContent;

    /**
     * 文章封面
     */
    @ApiModelProperty(name = "blogCover", value = "文章缩略图", dataType = "String")
    private String blogCover;

    /**
     * 文章分类
     */
    @ApiModelProperty(name = "category", value = "文章分类", dataType = "Integer")
    private String categoryName;

    /**
     * 文章标签
     */
    @ApiModelProperty(name = "tagNameList", value = "文章标签", dataType = "List<Integer>")
    private List<String> tagNameList;

    /**
     * 文章类型
     */
    @ApiModelProperty(name = "type", value = "文章类型", dataType = "Integer")
    private Integer type;

    /**
     * 文章作者
     */
    @ApiModelProperty(name = "author", value = "文章作者", dataType = "String")
    private String author;

    /**
     * 原文链接
     */
    @ApiModelProperty(name = "originalUrl", value = "原文链接", dataType = "String")
    private String originalUrl;

    /**
     * 是否置顶
     */
    @ApiModelProperty(name = "isTop", value = "是否置顶", dataType = "Integer")
    private Integer isTop;

    /**
     * 文章状态 1.公开 2.私密 3.评论可见
     */
    @ApiModelProperty(name = "status", value = "文章状态", dataType = "String")
    private Integer status;
}