package com.hongshu.controller;

import com.hongshu.dto.MessageBackDTO;
import com.hongshu.dto.MessageDTO;
import com.hongshu.service.MessageService;
import com.hongshu.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 留言控制器
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Api(tags = "留言模块")
@RestController
public class MessageController
{
    @Autowired
    private MessageService messageService;

    /**
     * 添加留言
     *
     * @param messageVO 留言信息
     * @return {@link Result}
     */
    @ApiOperation(value = "添加留言")
    @PostMapping("/messages")
    public Result saveMessage(@Valid @RequestBody MessageVO messageVO)
    {
        messageService.saveMessage(messageVO);
        return Result.ok();
    }

    /**
     * 查看留言列表
     *
     * @return {@link Result} 留言列表
     */
    @ApiOperation(value = "查看留言列表")
    @GetMapping("/messages")
    public Result listMessages()
    {
        List<MessageDTO> messageList = messageService.listMessages();
        return Result.ok().data("messageList",messageList);
    }

    /**
     * 查看后台留言列表
     *
     * @param condition 条件
     * @return {@link Result} 留言列表
     */
    @ApiOperation(value = "查看后台留言列表")
    @GetMapping("/admin/messages")
    public Result listMessageBackDTO(ConditionVO condition)
    {
        PageResult<MessageBackDTO> messageBackList = messageService.listMessageBackDTO(condition);
        return Result.ok().data("messageBackList",messageBackList);
    }

    /**
     * 审核留言
     *
     * @param reviewVO 审核信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "审核留言")
    @PutMapping("/admin/messages/review")
    public Result updateMessagesReview(@Valid @RequestBody ReviewVO reviewVO)
    {
        messageService.updateMessagesReview(reviewVO);
        return Result.ok();
    }

    /**
     * 删除留言
     *
     * @param messageIdList 留言id列表
     * @return {@link Result}
     */
    @ApiOperation(value = "删除留言")
    @DeleteMapping("/admin/messages")
    public Result deleteMessages(@RequestBody List<Integer> messageIdList) {
        messageService.removeByIds(messageIdList);
        return Result.ok();
    }
}
