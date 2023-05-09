package com.hongshu.handler;

import com.hongshu.exception.BlogException;
import com.hongshu.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hongshu.enums.StatusCodeEnum.SYSTEM_ERROR;

/**
 * 全局异常处理
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@RestControllerAdvice
public class ControllerAdviceHandler
{
    /**
     * 处理服务异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = BlogException.class)
    public Result errorHandler(BlogException e)
    {
        e.printStackTrace();
        return Result.error().codeAndMessage(e.getCode(),e.getMessage());
    }


    /**
     * 处理系统异常
     *
     * @param e 异常
     * @return 接口异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception e) {
        e.printStackTrace();
        return Result.state(false,SYSTEM_ERROR);
    }
}
