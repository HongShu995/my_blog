package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.constant.CommonConstant;
import com.hongshu.dao.UserAuthDao;
import com.hongshu.dao.UserInfoDao;
import com.hongshu.dao.UserRoleDao;
import com.hongshu.dto.UserBackDTO;
import com.hongshu.entity.UserAuth;
import com.hongshu.entity.UserInfo;
import com.hongshu.entity.UserRole;
import com.hongshu.enums.LoginTypeEnum;
import com.hongshu.enums.RoleEnum;
import com.hongshu.exception.BlogException;
import com.hongshu.service.BlogInfoService;
import com.hongshu.service.EmailService;
import com.hongshu.service.UserAuthService;
import com.hongshu.util.PageUtils;
import com.hongshu.util.UserUtil;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.PasswordVO;
import com.hongshu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;


/**
 * 用户授权服务类实例
 *
 * @author Hong_Shu995
 * @date 2021-12-07
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthDao, UserAuth> implements UserAuthService
{
    @Autowired
    private UserAuthDao userAuthDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private BlogInfoService blogInfoService;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void sendCode(String username)
    {
       emailService.sendCode(username);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserVO user)
    {
        //创建链接
        String sCode;
        String sCount;
        try (Jedis jedis = new Jedis("192.168.2.100", 6379)) {
            String username = user.getUsername();
            sCode = jedis.get("Code:" + username);
            sCount = jedis.get("Count:" + username);
        }
        System.out.println("验证码是："+sCode);
        System.out.println("次数是："+sCount);
        //检查注册信息大于是否三次
        if (Integer.parseInt(sCount)>=3){
            throw new BlogException("用户注册信息大于三次！请一天后再试");
        }
        //检查验证码是否过期
        if (!user.getCode().equals(sCode)){
            throw new BlogException("验证码已过期！");
        }
        // 校验账号是否合法
        if (checkUser(user)) {
            throw new BlogException("邮箱已被注册！");
        }
        // 检查验证码是否正确或者过期
        if (!user.getCode().equals(emailService.getEmailCode().get(user.getUsername())))
        {
            throw new BlogException("验证码错误！");
        }


        // 新增用户信息
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(CommonConstant.DEFAULT_NICKNAME + IdWorker.getId())
                .avatar(blogInfoService.getWebsiteConfig().getUserAvatar())
                .build();
        userInfoDao.insert(userInfo);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleDao.insert(userRole);
        // 新增用户账号
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .loginType(LoginTypeEnum.EMAIL.getType())
                .build();
        userAuthDao.insert(userAuth);

    }

    @Override
    public List<String> getUserRolesById(Integer id)
    {
        return this.baseMapper.selectUserRolesById(id);
    }

    @Override
    public PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition)
    {
        // 获取后台用户数量
        Integer count = userAuthDao.countUser(condition);
        if (count == 0) {
            return new PageResult<>();
        }
        // 获取后台用户列表
        List<UserBackDTO> userBackDTOList = userAuthDao.listUsers(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(userBackDTOList, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(UserVO user)
    {
        // 校验账号是否合法
        if (!checkUser(user)) {
            throw new BlogException("用户不存在！");
        }
        // 根据用户名修改密码
        userAuthDao.update(new UserAuth(), new LambdaUpdateWrapper<UserAuth>()
                .set(UserAuth::getPassword, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .eq(UserAuth::getUsername, user.getUsername()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAdminPassword(PasswordVO passwordVO)
    {
        // 查询旧密码是否正确
        UserAuth user = userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getId, UserUtil.getLoginUser().getId()));
        // 正确则修改密码，错误则提示不正确
        if (Objects.nonNull(user) && BCrypt.checkpw(passwordVO.getOldPassword(), user.getPassword())) {
            UserAuth userAuth = UserAuth.builder()
                    .id(UserUtil.getLoginUser().getId())
                    .password(BCrypt.hashpw(passwordVO.getNewPassword(), BCrypt.gensalt()))
                    .build();
            userAuthDao.updateById(userAuth);
        } else {
            throw new BlogException("旧密码不正确");
        }
    }

    /**
     * 查询用户名是否存在
     *
     * @param user 用户数据
     * @return 结果
     */
    private Boolean checkUser(UserVO user) {
        UserAuth userAuth = userAuthDao.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername()));
        return Objects.nonNull(userAuth);
    }
}
