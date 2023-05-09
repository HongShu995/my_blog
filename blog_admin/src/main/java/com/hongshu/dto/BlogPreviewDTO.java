package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 预览文章
 *
 * @author Hong_Shu995
 * @date 2021-12-20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPreviewDTO
{

    /**
     * 文章id
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
     * 发表时间
     */
    private Date createTime;

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


}
