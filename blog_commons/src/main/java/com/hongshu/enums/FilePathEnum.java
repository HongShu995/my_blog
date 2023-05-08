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
public enum FilePathEnum
{
    /**
     * 头像路径
     */
    AVATAR("images/tx/", "头像路径"),
    /**
     * 文章图片路径
     */
    ARTICLE("images/covers/", "文章图片路径"),
    /**
     * 音频路径
     */
    VOICE("voice/", "音频路径"),
    /**
     * 照片路径
     */
    PHOTO("images/photos/","相册路径"),
    /**
     * 配置图片路径
     */
    CONFIG("images/config/","配置图片路径");

    /**
     * 路径
     */
    private final String path;

    /**
     * 描述
     */
    private final String desc;
}
