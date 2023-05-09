package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 资源
 *
 * @author Hong_Shu995
 * @date 2021-12-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO
{

    /**
     * 权限id
     */
    private Integer id;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 权限路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 是否禁用
     */
    private Integer isDisable;

    /**
     * 是否匿名访问
     */
    private Integer isAnonymous;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 权限列表
     */
    private List<ResourceDTO> children;


}
