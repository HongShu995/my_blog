package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.entity.About;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关于我信息接口
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Mapper
public interface AboutDao extends BaseMapper<About>
{
}
