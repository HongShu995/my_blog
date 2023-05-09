package com.hongshu.dto;

import lombok.Data;

import java.util.List;

/**
 * 资源角色
 *
 * @author Hong_Shu995
 * @date 2021-12-15
 */
@Data
public class ResourceRoleDTO
{

    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 角色名
     */
    private List<String> roleList;

}
