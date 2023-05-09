package com.hongshu.controller;

import com.hongshu.dto.RoleDTO;
import com.hongshu.dto.UserRoleDTO;
import com.hongshu.service.RoleService;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.Result;
import com.hongshu.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-15
 */
@Api(tags = "角色模块")
@RestController
public class RoleController
{
    @Autowired
    private RoleService roleService;

    /**
     * 查询用户角色选项
     *
     * @return {@link Result} 用户角色选项
     */
    @ApiOperation(value = "查询用户角色选项")
    @GetMapping("/admin/users/role")
    public Result listUserRoles() {
        List<UserRoleDTO> userRoleDTOS = roleService.listUserRoles();
        return Result.ok().data("userRoleList",userRoleDTOS);
    }

    /**
     * 查询角色列表
     *
     * @param conditionVO 条件
     * @return {@link Result} 角色列表
     */
    @ApiOperation(value = "查询角色列表")
    @GetMapping("/admin/roles")
    public Result listRoles(ConditionVO conditionVO) {
        PageResult<RoleDTO> roleList = roleService.listRoles(conditionVO);
        return Result.ok().data("roleList",roleList);
    }

    /**
     * 保存或更新角色
     *
     * @param roleVO 角色信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "保存或更新角色")
    @PostMapping("/admin/role")
    public Result saveOrUpdateRole(@RequestBody @Valid RoleVO roleVO) {
        roleService.saveOrUpdateRole(roleVO);
        return Result.ok();
    }

    /**
     * 删除角色
     *
     * @param roleIdList 角色id列表
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/admin/roles")
    public Result deleteRoles(@RequestBody List<Integer> roleIdList) {
        roleService.deleteRoles(roleIdList);
        return Result.ok();
    }
}
