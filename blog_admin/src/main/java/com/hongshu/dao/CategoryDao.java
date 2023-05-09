package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.dto.CategoryBackDTO;
import com.hongshu.dto.CategoryDTO;
import com.hongshu.entity.Category;
import com.hongshu.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类接口
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category>
{
    /**
     * 查询分类和对应文章数量
     *
     * @return 分类列表
     */
    List<CategoryDTO> listCategoryDTO();

    /**
     * 查询后台分类列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List<CategoryBackDTO>} 分类列表
     */
    List<CategoryBackDTO> listCategoryBackDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

}
