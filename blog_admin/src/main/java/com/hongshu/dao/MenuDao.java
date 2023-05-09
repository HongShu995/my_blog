package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单类接口
 *
 * @author Hong_Shu995
 * @date 2021-12-09
 */
@Mapper
public interface MenuDao extends BaseMapper<Menu>
{
    /**
     * 根据用户信息id查询菜单
     * @param userInfoId 用户信息id
     * @return 菜单列表
     */
    List<Menu> getAllMenuListById(@Param("userInfoId")Integer userInfoId);
}
