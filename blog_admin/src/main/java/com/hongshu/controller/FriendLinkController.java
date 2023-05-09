package com.hongshu.controller;

import com.hongshu.dto.FriendLinkBackDTO;
import com.hongshu.dto.FriendLinkDTO;
import com.hongshu.service.FriendLinkService;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.FriendLinkVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 友链控制器
 *
 * @author Hong_Shu995
 * @date 2021-12-20
 */
@Api(tags = "友链模块")
@RestController
public class FriendLinkController
{
    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 查看友链列表
     *
     * @return {@link Result} 友链列表
     */
    @ApiOperation(value = "查看友链列表")
    @GetMapping("/links")
    public Result listFriendLinks() {
        List<FriendLinkDTO> friendLinks = friendLinkService.listFriendLinks();
        return Result.ok().data("friendLinks",friendLinks);
    }

    /**
     * 查看后台友链列表
     *
     * @param condition 条件
     * @return {@link Result} 后台友链列表
     */
    @ApiOperation("查看后台友链列表")
    @GetMapping("/admin/links")
    public Result listFriendLinkDTO(ConditionVO condition) {
        PageResult<FriendLinkBackDTO> friendLinkBackList = friendLinkService.listFriendLinkDTO(condition);
        return Result.ok().data("friendLinkBackList",friendLinkBackList);
    }

    /**
     * 保存或修改友链
     *
     * @param friendLinkVO 友链信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "保存或修改友链")
    @PostMapping("/admin/links")
    public Result saveOrUpdateFriendLink(@Valid @RequestBody FriendLinkVO friendLinkVO) {
        friendLinkService.saveOrUpdateFriendLink(friendLinkVO);
        return Result.ok();
    }

    /**
     * 删除友链
     *
     * @param linkIdList 友链id列表
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除友链")
    @DeleteMapping("/admin/links")
    public Result deleteFriendLink(@RequestBody List<Integer> linkIdList) {
        friendLinkService.removeByIds(linkIdList);
        return Result.ok();
    }
}
