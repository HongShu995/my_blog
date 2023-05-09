package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 后台标签
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagBackDTO
{
    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 文章量
     */
    private Integer blogCount;

    /**
     * 创建时间
     */
    private Date createTime;

}
