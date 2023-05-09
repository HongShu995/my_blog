package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO
{

    /**
     * id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;

}
