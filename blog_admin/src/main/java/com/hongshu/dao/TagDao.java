package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.dto.TagBackDTO;
import com.hongshu.entity.Tag;
import com.hongshu.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标签类接口
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Mapper
public interface TagDao extends BaseMapper<Tag>
{
    /**
     * 查询后台标签列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List <TagBackDTO>} 标签列表
     */
    List<TagBackDTO> listTagBackDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 根据文章id查询标签名
     *
     * @param blogId 文章id
     * @return {@link List<String>} 标签名列表
     */
    List<String> listTagNameByBlogId(Integer blogId);
}
