package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章上下篇
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPaginationDTO
{

    /**
     * id
     */
    private Integer id;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;
}
