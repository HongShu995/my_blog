package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hongshu.dao.RoleDao;
import com.hongshu.dao.UserInfoDao;
import com.hongshu.dto.UserDetailsDTO;
import com.hongshu.entity.UserAuth;
import com.hongshu.entity.UserInfo;
import com.hongshu.exception.BlogException;
import com.hongshu.service.UserAuthService;
import com.hongshu.util.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 用户详细信息服务
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RoleDao roleDao;

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if (StringUtils.isBlank(username)) {
            throw new BlogException("用户名不能为空！");
        }
        //获取用户名和密码
        QueryWrapper<UserAuth> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        UserAuth userAuth = userAuthService.getOne(wrapper);
        if(userAuth == null)
        {
            throw new UsernameNotFoundException("没有该用户");
        }
        // 封装登录信息
        return convertUserDetail(userAuth, request);
    }

    /**
     * 封装用户登录信息
     *
     * @param user    用户账号
     * @param request 请求
     * @return 用户登录信息
     */
    public UserDetailsDTO convertUserDetail(UserAuth user, HttpServletRequest request)
    {
        // 查询账号信息
        UserInfo userInfo = userInfoDao.selectById(user.getUserInfoId());
        // 查询账号角色
        List<String> roleList = roleDao.listRolesByUserInfoId(userInfo.getId());
        // 获取设备信息
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = IpUtils.getUserAgent(request);
        // 封装权限集合
        return UserDetailsDTO.builder()
                .id(user.getId())
                .loginType(user.getLoginType())
                .userInfoId(user.getUserInfoId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(userInfo.getEmail())
                .roleList(roleList)
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .isDisable(userInfo.getIsDisable())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOperatingSystem().getName())
                .lastLoginTime(new Date())
                .build();
    }


}
