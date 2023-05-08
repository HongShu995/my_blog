package com.hongshu.exception;

import com.hongshu.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hongshu.enums.StatusCodeEnum.ERROR;

/**
 * projectName: my_blog
 * <p>
 * &#064;author:  WangYiBing
 * time: 2023/5/8 14:10 周一
 * description:
 */
@Getter
@AllArgsConstructor
public class BlogException extends RuntimeException
{
    /**
     * 错误码
     */
    private Integer code = ERROR.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public BlogException(String message)
    {
        this.message = message;
    }

    public BlogException(StatusCodeEnum statusCodeEnum)
    {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
