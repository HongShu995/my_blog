package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章预览列表
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPreviewListDTO
{

    /**
     * 文章列表
     */
    private List<BlogPreviewDTO> blogPreviewDTOList;

    /**
     * 条件名
     */
    private String name;

}
