package com.hongshu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 网站配置
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "tb_website_config")
public class WebsiteConfig
{
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 配置信息
     */
    private String config;

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
