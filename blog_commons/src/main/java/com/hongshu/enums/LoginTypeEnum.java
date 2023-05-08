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
public enum LoginTypeEnum
{
    /**
     * 邮箱登录
     */
    EMAIL(1, "邮箱登录", ""),
    /**
     * QQ登录
     */
    QQ(2, "QQ登录", "qqLoginStrategyImpl"),
    /**
     * 微博登录
     */
    WEIBO(3, "微博登录", "weiboLoginStrategyImpl");

    /**
     * 登录方式
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 策略
     */
    private final String strategy;

}
