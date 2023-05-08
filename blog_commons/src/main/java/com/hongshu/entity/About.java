package com.hongshu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 关于我页面信息
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_about")
public class About
{
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date update_time;

}
