package com.hongshu.controller;

import com.hongshu.dto.UserBackDTO;
import com.hongshu.service.UserAuthService;
import com.hongshu.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户授权控制类
 *
 * @author Hong_Shu995
 * @date 2021-12-03
 */
@Api(tags = "用户账号模块")
@RestController
public class UserAuthController
{
    @Autowired
    private UserAuthService userAuthService;

    /**
     * 发送邮箱验证码
     *
     * @param username 用户名
     * @return {@link Result}
     */
    @ApiOperation(value = "发送邮箱验证码")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/users/code")
    public Result sendCode(String username) {
        userAuthService.sendCode(username);
        return Result.ok();
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserVO user)
    {
        userAuthService.register(user);
        return Result.ok();
    }

    /**
     * 修改密码
     *
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "修改密码")
    @PutMapping("/users/password")
    public Result updatePassword(@Valid @RequestBody UserVO user) {
        userAuthService.updatePassword(user);
        return Result.ok();
    }

    /**
     * 查询后台用户列表
     *
     * @return {@link Result} 用户列表
     */
    @ApiOperation(value = "查询后台用户列表")
    @GetMapping("/admin/users")
    public Result listUsers(ConditionVO condition) {
        PageResult<UserBackDTO> userBackDTOS = userAuthService.listUserBackDTO(condition);
        return Result.ok().data("userBackDTOS",userBackDTOS);
    }

    /**
     * 修改管理员密码
     *
     * @param passwordVO 密码信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "修改管理员密码")
    @PutMapping("/admin/updatePassword")
    public Result updateAdminPassword(@Valid @RequestBody PasswordVO passwordVO)
    {
        userAuthService.updateAdminPassword(passwordVO);
        return Result.ok();
    }
}
