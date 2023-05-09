package com.hongshu.service;

import java.util.Map;

/**
 * 发送邮件
 *
 * @author Hong_Shu995
 * @date 2021-12-30
 */
public interface EmailService
{
    /**
     * 发送验证码
     *
     * @param username
     */
    void sendCode(String username);

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    Map<String,String> getEmailCode();
}
