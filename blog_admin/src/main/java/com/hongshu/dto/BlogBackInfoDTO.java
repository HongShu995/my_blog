package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 博客后台信息
 *
 * @author Hong_Shu995
 * @date 2021-12-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogBackInfoDTO
{

    /**
     * 用户量
     */
    private Long userCount;

    /**
     * 文章量
     */
    private Long blogCount;

    /**
     * 文章类型量
     */
    private Long categoriseCount;

    /**
     * 文章标签量
     */
    private Long tagsCount;

    /**
     * 文章类型列表
     */
    private List<CategoryDTO> categoryDTOList;

    /**
     * 文章标签列表
     */
    private List<TagDTO> tagDTOList;
}
