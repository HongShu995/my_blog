package com.hongshu.service.impl;

import com.hongshu.exception.BlogException;
import com.hongshu.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.hongshu.util.CommonUtils.checkEmail;
import static com.hongshu.util.CommonUtils.getRandomCode;

/**
 * 发送邮件
 *
 * @author Hong_Shu995
 * @date 2021-12-30
 */
@Service
public class EmailServiceImpl implements EmailService
{
    @Resource
//    JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String sender;

    private Map<String,String> codes = new HashMap<>();

    @Override
    public void sendCode(String username)
    {
        // 校验账号是否合法
        if (!checkEmail(username)) {
            throw new BlogException("请输入正确邮箱");
        }
        // 生成六位随机验证码发送
        String code = getRandomCode();
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(sender);
        //接收者
        mainMessage.setTo(username);
        //发送的标题
        mainMessage.setSubject("【Hong_Shu个人博客】注册账号验证码");
        //发送的内容
        String msg = "您好！" + username + ",您正在使用邮箱验证，验证码：" + code + "有效期为5分钟,如非本人操作,请忽略该邮件。（这是一封系统邮件，请勿回复）";
        mainMessage.setText(msg);
        //发送邮件
//        jms.send(mainMessage);
        //存储验证码用于验证
        this.codes.put(username,code);
        //设置验证码有效期限
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//                if(codes.get(username) != null)
//                {
//                    codes.remove(username);
//                }
//            }
//        },5*60*1000);
    }


    @Override
    public Map<String,String> getEmailCode()
    {
        return this.codes;
    }
}
