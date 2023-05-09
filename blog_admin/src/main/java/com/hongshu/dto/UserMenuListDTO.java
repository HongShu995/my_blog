package com.hongshu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户菜单列表
 *
 * @author Hong_Shu995
 * @date 2021-12-12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMenuListDTO
{
    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 是否隐藏
     */
    private boolean hidden;

    /**
     * 子菜单
     */
    private List<UserMenuListDTO> children;

    public UserMenuListDTO getInfo(MenuDTO menuDTO)
    {
        this.setName(menuDTO.getName());
        this.setPath(menuDTO.getPath());
        this.setIcon(menuDTO.getIcon());
        this.setComponent(menuDTO.getComponent());
        this.setHidden(menuDTO.getIsHidden() != 0);
        return this;
    }

}
