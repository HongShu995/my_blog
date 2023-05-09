package com.hongshu.handler;

import com.alibaba.fastjson.JSON;
import com.hongshu.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限处理
 *
 * @author Hong_Shu995
 * @date 2021-12-15
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException
    {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error().message("权限不足")));
    }
}
