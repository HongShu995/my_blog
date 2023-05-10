package com.hongshu.service.impl;

import com.hongshu.exception.BlogException;
import com.hongshu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

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
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private MailService mailService;

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
        jms.send(mainMessage);
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
//        System.out.println("发送邮件");
//        mailService.sendSimpleMail("2212977363@qq.com","普通文本邮件","普通文本邮件内容");
        //创建链接
        Jedis jedis=new Jedis("192.168.2.100",6379);
        //设置账号次数的key
        String countKey="Count:"+username;
        //设置验证码的key
        String codeKey="Code:"+username;
        //获取一下今天的次数
        String count = jedis.get(countKey);
        //对count 进行判断
        if(count==null){
            //今天还没发送验证码
            //设置时间和次数
            jedis.setex(countKey,24*60*60,"1");
            //把验证码放进去，两分钟过期
            jedis.setex(codeKey,2*60,code);
            jedis.close();
        }else if(Integer.parseInt(count)<=2){
            //不是第一次但是没超过限制
            jedis.incr(countKey);
            //把验证码放进去，两分钟过期
            jedis.setex(codeKey,2*60,code);
            jedis.close();
        }else if(Integer.parseInt(count)>2){
            //超过限制
            System.out.println("今天发送的次数已经超过了三次");
            //把验证码删除掉
            jedis.del(codeKey);
        }

    }


    @Override
    public Map<String,String> getEmailCode()
    {
        return this.codes;
    }

}
