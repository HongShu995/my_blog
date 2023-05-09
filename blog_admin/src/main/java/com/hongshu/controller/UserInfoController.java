package com.hongshu.controller;

import com.hongshu.service.UserInfoService;
import com.hongshu.vo.Result;
import com.hongshu.vo.UserInfoVO;
import com.hongshu.vo.UserRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * 用户信息控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-08
 */
@RestController
@Api(tags = "用户信息模块")
public class UserInfoController
{
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 更新用户信息
     *
     * @param userInfoVO 用户信息
     * @return {@link Result}
     */
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/users/info")
    public Result updateUserInfo(@Valid @RequestBody UserInfoVO userInfoVO)
    {
        userInfoService.updateUserInfo(userInfoVO);
        return Result.ok();
    }

    /**
     * 修改用户角色
     *
     * @param userRoleVO 用户角色信息
     * @return {@link Result}
     */
    @ApiOperation(value = "修改用户角色")
    @PutMapping("/admin/users/role")
    public Result updateUserRole(@Valid @RequestBody UserRoleVO userRoleVO)
    {
        userInfoService.updateUserRole(userRoleVO);
        return Result.ok();
    }

    /**
     * 更新用户头像
     *
     * @param file 文件
     * @return {@link Result} 头像地址
     */
    @ApiOperation(value = "更新用户头像")
    @ApiImplicitParam(name = "file", value = "用户头像", required = true, dataType = "MultipartFile")
    @PostMapping("/users/avatar")
    public Result updateUserAvatar(MultipartFile file)
    {
        String path = userInfoService.updateUserAvatar(file);
        return Result.ok().data("path",path);
    }

}
