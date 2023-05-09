package com.hongshu.handler;

import com.alibaba.fastjson.JSON;
import com.hongshu.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录处理
 *
 * @author Hong_Shu995
 * @date 2021-12-15
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException
    {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error().message("用户未登录")));
    }
}
