package com.hongshu.dto;

import com.hongshu.vo.PageVO;
import com.hongshu.vo.WebsiteConfigVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 博客首页信息
 *
 * @author Hong_Shu995
 * @date 2021-12-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogHomeInfoDTO
{

    /**
     * 文章数量
     */
    private Long blogCount;

    /**
     * 分类数量
     */
    private Long categoryCount;

    /**
     * 标签数量
     */
    private Long tagCount;

    /**
     * 访问量
     */
    private String viewsCount;

    /**
     * 网站配置
     */
    private WebsiteConfigVO websiteConfig;

    /**
     * 页面列表
     */
    private List<PageVO> pageList;


}
