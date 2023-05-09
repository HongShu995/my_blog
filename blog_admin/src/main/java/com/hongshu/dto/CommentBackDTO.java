package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBackDTO
{
    /**
     * id
     */
    private Integer id;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 被回复用户昵称
     */
    private String replyNickname;

    /**
     * 文章标题
     */
    private String blogTitle;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 是否审核
     */
    private Integer isReview;

    /**
     * 发表时间
     */
    private Date createTime;

}
