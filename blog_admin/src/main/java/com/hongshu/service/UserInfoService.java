package com.hongshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongshu.entity.UserInfo;
import com.hongshu.vo.UserInfoVO;
import com.hongshu.vo.UserRoleVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
public interface UserInfoService extends IService<UserInfo>
{
    /**
     * 修改用户资料
     *
     * @param userInfoVO 用户资料
     */
    void updateUserInfo(UserInfoVO userInfoVO);

    /**
     * 修改用户头像
     *
     * @param file 头像图片
     * @return 头像地址
     */
    String updateUserAvatar(MultipartFile file);

    /**
     * 更新用户角色
     *
     * @param userRoleVO 更新用户角色
     */
    void updateUserRole(UserRoleVO userRoleVO);
}
