package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 首页文章
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogHomeDTO
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
     * 发表时间
     */
    private LocalDateTime createTime;

    /**
     * 是否置顶
     */
    private Integer isTop;

    /**
     * 文章类型
     */
    private Integer type;

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
