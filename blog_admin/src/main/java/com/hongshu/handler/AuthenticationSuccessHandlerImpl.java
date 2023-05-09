package com.hongshu.handler;

import com.alibaba.fastjson.JSON;
import com.hongshu.dao.UserAuthDao;
import com.hongshu.dto.UserInfoDTO;
import com.hongshu.entity.UserAuth;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.util.UserUtil;
import com.hongshu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hongshu.enums.StatusCodeEnum.LOGIN_SUCCESS;

/**
 * 认证成功处理
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler
{
    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException
    {

        // 返回登录信息
        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(UserUtil.getLoginUser(), UserInfoDTO.class);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.state(true,LOGIN_SUCCESS).data("userLoginDTO", userLoginDTO)));
        // 更新用户ip，最近登录时间
        updateUserInfo();
    }

    /**
     * 更新用户信息
     */
    @Async
    public void updateUserInfo() {
        UserAuth userAuth = UserAuth.builder()
                .id(UserUtil.getLoginUser().getId())
                .ipAddress(UserUtil.getLoginUser().getIpAddress())
                .ipSource(UserUtil.getLoginUser().getIpSource())
                .lastLoginTime(UserUtil.getLoginUser().getLastLoginTime())
                .build();
        userAuthDao.updateById(userAuth);
    }
}
