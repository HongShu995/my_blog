package com.hongshu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章标签
 * 标签
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_blog_tag")
public class BlogTag
{

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    private Integer blogId;

    /**
     * 标签id
     */
    private Integer tagId;

}
