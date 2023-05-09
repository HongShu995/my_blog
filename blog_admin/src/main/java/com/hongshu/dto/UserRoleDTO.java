package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色选项
 *
 * @author Hong_Shu995
 * @date 2021-12-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO
{

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

}
