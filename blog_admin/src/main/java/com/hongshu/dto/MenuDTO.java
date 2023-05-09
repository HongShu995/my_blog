package com.hongshu.dto;

import com.hongshu.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 菜单
 *
 * @author Hong_Shu995
 * @date 2021-12-09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO
{

    /**
     * id
     */
    private Integer id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * icon
     */
    private String icon;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否禁用
     */
    private Integer isDisable;

    /**
     * 是否隐藏
     */
    private Integer isHidden;

    /**
     * 子菜单列表
     */
    private List<MenuDTO> children;


    public void addMenu(Menu menu)
    {
        this.id = menu.getId();
        this.name = menu.getName();
        this.path = menu.getPath();
        this.component = menu.getComponent();
        this.icon = menu.getIcon();
        this.createTime = menu.getCreateTime();
        this.updateTime = menu.getUpdateTime();
        this.orderNum = menu.getOrderNum();
        this.isHidden = menu.getIsHidden();
    }

    public MenuDTO isChildren(Menu menu)
    {
        this.id = menu.getId();
        this.name = menu.getName();
        this.path = menu.getPath();
        this.component = menu.getComponent();
        this.icon = menu.getIcon();
        this.createTime = menu.getCreateTime();
        this.updateTime = menu.getUpdateTime();
        this.orderNum = menu.getOrderNum();
        this.isHidden = menu.getIsHidden();
        return this;
    }


}
