package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.FriendLinkDao;
import com.hongshu.dto.FriendLinkBackDTO;
import com.hongshu.dto.FriendLinkDTO;
import com.hongshu.entity.FriendLink;
import com.hongshu.service.FriendLinkService;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.util.PageUtils;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.FriendLinkVO;
import com.hongshu.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 友情链接服务
 *
 * @author Hong_Shu995
 * @date 2021-12-20
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkDao, FriendLink> implements FriendLinkService
{
    @Autowired
    private FriendLinkDao friendLinkDao;

    @Override
    public List<FriendLinkDTO> listFriendLinks()
    {
        // 查询友链列表
        List<FriendLink> friendLinkList = friendLinkDao.selectList(null);
        return BeanCopyUtils.copyList(friendLinkList, FriendLinkDTO.class);
    }

    @Override
    public PageResult<FriendLinkBackDTO> listFriendLinkDTO(ConditionVO condition)
    {
        // 分页查询友链列表
        Page<FriendLink> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        Page<FriendLink> friendLinkPage = friendLinkDao.selectPage(page, new LambdaQueryWrapper<FriendLink>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), FriendLink::getLinkName, condition.getKeywords()));
        // 转换DTO
        List<FriendLinkBackDTO> friendLinkBackDTOList = BeanCopyUtils.copyList(friendLinkPage.getRecords(), FriendLinkBackDTO.class);
        return new PageResult<>(friendLinkBackDTOList, (int) friendLinkPage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO)
    {
        FriendLink friendLink = BeanCopyUtils.copyObject(friendLinkVO, FriendLink.class);
        this.saveOrUpdate(friendLink);
    }
}

