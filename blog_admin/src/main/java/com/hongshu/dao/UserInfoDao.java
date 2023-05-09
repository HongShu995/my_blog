package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo>
{
}
