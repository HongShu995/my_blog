package com.hongshu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_comment")
public class Comment
{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论用户Id
     */
    private Integer userId;

    /**
     * 回复用户id
     */
    private Integer replyUserId;

    /**
     * 评论文章id
     */
    private Integer blogId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 父评论id
     */
    private Integer parentId;

    /**
     * 是否审核
     */
    private Integer isReview;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}
