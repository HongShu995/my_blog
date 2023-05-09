package com.hongshu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 友链VO
 *
 * @author Hong_Shu995
 * @date 2021-12-20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "友链")
public class FriendLinkVO
{
    /**
     * id
     */
    @ApiModelProperty(name = "Id", value = "友链id", dataType = "Integer")
    private Integer id;

    /**
     * 链接名
     */
    @NotBlank(message = "链接名不能为空")
    @ApiModelProperty(name = "linkName", value = "友链名", required = true, dataType = "String")
    private String linkName;

    /**
     * 链接头像
     */
    @NotBlank(message = "链接头像不能为空")
    @ApiModelProperty(name = "linkAvatar", value = "友链头像", required = true, dataType = "String")
    private String linkAvatar;

    /**
     * 链接地址
     */
    @NotBlank(message = "链接地址不能为空")
    @ApiModelProperty(name = "linkAddress", value = "友链地址", required = true, dataType = "String")
    private String linkAddress;

    /**
     * 介绍
     */
    @NotBlank(message = "链接介绍不能为空")
    @ApiModelProperty(name = "linkIntro", value = "友链介绍", required = true, dataType = "String")
    private String linkIntro;
}
