package com.hongshu.util;

import com.hongshu.dto.UserDetailsDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * projectName: my_blog
 * <p>
 * &#064;author:  WangYiBing
 * time: 2023/5/8 14:19 周一
 * description:
 */
@Component
public class UserUtil
{
    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetailsDTO getLoginUser() {
        return (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
