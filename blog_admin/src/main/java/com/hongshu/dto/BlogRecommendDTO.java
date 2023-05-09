package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 推荐文章
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogRecommendDTO
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
