package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryBackDTO
{

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 文章数量
     */
    private Integer blogCount;

    /**
     * 创建时间
     */
    private Date createTime;

}