package com.hongshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongshu.dto.UserBackDTO;
import com.hongshu.entity.UserAuth;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.PasswordVO;
import com.hongshu.vo.UserVO;

import java.util.List;

/**
 * 用户授权服务类接口
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
public interface UserAuthService extends IService<UserAuth>
{
    /**
     * 发送验证码
     *
     * @param username
     */
    void sendCode(String username);

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    void register(UserVO user);

    /**
     * 根据id获取用户的角色
     * @param id 用户id
     * @return 角色列表
     */
    List<String> getUserRolesById(Integer id);

    /**
     * 查询后台用户列表
     * @return
     */
    PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition);

    /**
     * 修改密码
     *
     * @param user 用户对象
     */
    void updatePassword(UserVO user);

    /**
     * 修改管理员密码
     *
     * @param passwordVO 密码对象
     */
    void updateAdminPassword(PasswordVO passwordVO);
}
