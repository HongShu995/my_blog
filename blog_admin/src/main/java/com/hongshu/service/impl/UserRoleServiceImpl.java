package com.hongshu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.UserRoleDao;
import com.hongshu.entity.UserRole;
import com.hongshu.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService
{
}
