package com.hongshu.controller;

import com.hongshu.dto.LabelOptionDTO;
import com.hongshu.dto.MenuDTO;
import com.hongshu.dto.UserMenuDTO;
import com.hongshu.service.MenuService;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.MenuVO;
import com.hongshu.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-09
 */
@Api(tags = "菜单模块")
@RestController
public class MenuController
{
    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     *
     * @param conditionVO 条件
     * @return {@link Result<MenuDTO>} 菜单列表
     */
    @ApiOperation(value = "查看菜单列表")
    @GetMapping("/admin/menus")
    public Result listMenus(ConditionVO conditionVO) {
        List<MenuDTO> menuList = menuService.listMenus(conditionVO);
        return Result.ok().data("menuList",menuList);
    }

    /**
     * 新增或修改菜单
     *
     * @param menuVO 菜单
     * @return {@link Result}
     */
    @ApiOperation(value = "新增或修改菜单")
    @PostMapping("/admin/menus")
    public Result saveOrUpdateMenu(@Valid @RequestBody MenuVO menuVO) {
        menuService.saveOrUpdateMenu(menuVO);
        return Result.ok();
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return {@link Result}
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/admin/menus/{menuId}")
    public Result deleteMenu(@PathVariable("menuId") Integer menuId){
        menuService.deleteMenu(menuId);
        return Result.ok();
    }

    /**
     * 查看角色菜单选项
     *
     * @return {@link Result} 查看角色菜单选项
     */
    @ApiOperation(value = "查看角色菜单选项")
    @GetMapping("/admin/role/menus")
    public Result listMenuOptions() {
        List<LabelOptionDTO> roleMenuList = menuService.listMenuOptions();
        return Result.ok().data("roleMenuList",roleMenuList);
    }

    /**
     * 查看当前用户菜单
     *
     * @return {@link Result} 菜单列表
     */
    @ApiOperation(value = "查看当前用户菜单")
    @GetMapping("/admin/user/menus")
    public Result listUserMenus() {
        List<UserMenuDTO> userMenuList = menuService.listUserMenus();
        return Result.ok().data("userMenuList",userMenuList);
    }

}
