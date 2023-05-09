package com.hongshu.controller;

import com.hongshu.dto.CommentBackDTO;
import com.hongshu.dto.CommentDTO;
import com.hongshu.dto.ReplyDTO;
import com.hongshu.service.CommentService;
import com.hongshu.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Api(tags = "评论模块")
@RestController
public class CommentController
{
    @Autowired
    private CommentService commentService;

    /**
     * 查询评论
     *
     * @param blogId 文章id
     * @return {@link Result} 评论列表
     */
    @ApiOperation(value = "查询评论")
    @ApiImplicitParam(name = "blogId", value = "文章id", required = true, dataType = "Integer")
    @GetMapping("/comments")
    public Result listComments(Integer blogId)
    {
        PageResult<CommentDTO> commentList = commentService.listComments(blogId);
        return Result.ok().data("commentList",commentList);
    }

    /**
     * 添加评论
     *
     * @param commentVO 评论信息
     * @return {@link Result}
     */
    @ApiOperation(value = "添加评论")
    @PostMapping("/comments")
    public Result saveComment(@Valid @RequestBody CommentVO commentVO)
    {
        commentService.saveComment(commentVO);
        return Result.ok();
    }

    /**
     * 查询评论下的回复
     *
     * @param commentId 评论id
     * @return {@link Result} 回复列表
     */
    @ApiOperation(value = "查询评论下的回复")
    @ApiImplicitParam(name = "commentId", value = "评论id", required = true, dataType = "Integer")
    @GetMapping("/comments/{commentId}/replies")
    public Result listRepliesByCommentId(@PathVariable("commentId") Integer commentId)
    {
        List<ReplyDTO> replyList = commentService.listRepliesByCommentId(commentId);
        return Result.ok().data("replyList",replyList);
    }

    /**
     * 审核评论
     *
     * @param reviewVO 审核信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "审核评论")
    @PutMapping("/admin/comments/review")
    public Result updateCommentsReview(@Valid @RequestBody ReviewVO reviewVO) {
        commentService.updateCommentsReview(reviewVO);
        return Result.ok();
    }

    /**
     * 删除评论
     *
     * @param commentIdList 评论id列表
     * @return {@link Result<>}
     */
    @ApiOperation(value = "删除评论")
    @DeleteMapping("/admin/comments")
    public Result deleteComments(@RequestBody List<Integer> commentIdList) {
        commentService.removeByIds(commentIdList);
        return Result.ok();
    }

    /**
     * 查询后台评论
     *
     * @param condition 条件
     * @return {@link Result} 后台评论
     */
    @ApiOperation(value = "查询后台评论")
    @GetMapping("/admin/comments")
    public Result listCommentBackDTO(ConditionVO condition) {
        PageResult<CommentBackDTO> commentBackList = commentService.listCommentBackDTO(condition);
        return Result.ok().data("commentBackList",commentBackList);
    }
}
