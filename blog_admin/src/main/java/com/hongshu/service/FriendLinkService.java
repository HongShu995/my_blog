package com.hongshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongshu.dto.FriendLinkBackDTO;
import com.hongshu.dto.FriendLinkDTO;
import com.hongshu.entity.FriendLink;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.FriendLinkVO;
import com.hongshu.vo.PageResult;

import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2021-12-20
 */
public interface FriendLinkService extends IService<FriendLink>
{

    /**
     * 查看友链列表
     *
     * @return 友链列表
     */
    List<FriendLinkDTO> listFriendLinks();

    /**
     * 查看后台友链列表
     *
     * @param condition 条件
     * @return 友链列表
     */
    PageResult<FriendLinkBackDTO> listFriendLinkDTO(ConditionVO condition);

    /**
     * 保存或更新友链
     *
     * @param friendLinkVO 友链
     */
    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
