package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.dto.BlogBackDTO;
import com.hongshu.dto.BlogDTO;
import com.hongshu.dto.BlogHomeDTO;
import com.hongshu.dto.BlogPreviewDTO;
import com.hongshu.entity.Blog;
import com.hongshu.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客类接口
 *
 * @author Hong_Shu995
 * @date 2021-12-13
 */
@Mapper
public interface BlogDao extends BaseMapper<Blog>
{
    /**
     * 查询后台文章总量
     *
     * @param condition 条件
     * @return 文章总量
     */
    Integer countBlogBacks(@Param("condition") ConditionVO condition);

    /**
     * 查询后台文章
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return 文章列表
     */
    List<BlogBackDTO> listBlogBacks(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 查询首页文章
     *
     * @param current 页码
     * @param size    大小
     * @return 文章列表
     */
    List<BlogHomeDTO> listBlogs(@Param("current") Long current, @Param("size") Long size);

    /**
     * 根据id查询文章
     *
     * @param blogId 文章id
     * @return 文章信息
     */
    BlogDTO getBlogById(@Param("blogId") Integer blogId);

    /**
     * 根据条件查询文章
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return 文章列表
     */
    List<BlogPreviewDTO> listBlogsByCondition(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

}
