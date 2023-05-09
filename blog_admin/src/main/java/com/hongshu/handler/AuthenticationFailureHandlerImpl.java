package com.hongshu.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongshu.vo.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hongshu.enums.StatusCodeEnum.LOGIN_ERROR;
/**
 * 验证失败处理
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(Result.state(false, LOGIN_ERROR).getMessage()));
    }

}