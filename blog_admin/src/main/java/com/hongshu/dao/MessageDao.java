package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Mapper
public interface MessageDao extends BaseMapper<Message>
{
}
