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
public enum BlogStatusEnum
{
    /**
     * 公开
     */
    PUBLIC(1, "公开"),
    /**
     * 私密
     */
    SECRET(2, "私密"),
    /**
     * 草稿
     */
    DRAFT(3, "草稿");

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;

}
