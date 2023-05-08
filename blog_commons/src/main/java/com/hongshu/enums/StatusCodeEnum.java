package com.hongshu.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * projectName: my_blog
 *
 * @author: WangYiBing
 * time: 2023/5/8 14:10 周一
 * description:
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum
{
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 错误
     */
    ERROR(101,"操作错误"),
    /**
     * 登录验证成功
     */
    LOGIN_SUCCESS(666,"登录验证成功"),
    /**
     * 登录验证失败
     */
    LOGIN_ERROR(777,"登录验证失败"),
    /**
     * 没有操作权限
     */
    AUTHORIZED(403, "没有操作权限"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(500, "系统异常"),
    /**
     * 用户名已存在
     */
    USERNAME_EXIST(521, "用户名已存在"),
    /**
     * 用户名不存在
     */
    USERNAME_NOT_EXIST(522, "用户名不存在"),
    /**
     * 没有找到
     */
    NOT_FOUND(404,"没有找到");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    StatusCodeEnum(String desc)
    {
        this.code = 333;
        this.desc = desc;
    }
}
