package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.dto.UserBackDTO;
import com.hongshu.entity.UserAuth;
import com.hongshu.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户授权类接口
 *
 * @author Hong_Shu995
 * @date 2021-12-03
 */
@Mapper
public interface UserAuthDao extends BaseMapper<UserAuth>
{
    /**
     * 查询后台用户列表
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return {@link List<UserBackDTO>} 用户列表
     */
    List<UserBackDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);


    /**
     * 根据用户id获取角色
     * @return
     */
    List<String> selectUserRolesById(@Param("id") Integer id);

    /**
     * 查询后台用户数量
     *
     * @param condition 条件
     * @return 用户数量
     */
    Integer countUser(@Param("condition") ConditionVO condition);
}
