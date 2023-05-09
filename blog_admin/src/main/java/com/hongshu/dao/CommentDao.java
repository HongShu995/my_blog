package com.hongshu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongshu.dto.CommentBackDTO;
import com.hongshu.dto.CommentDTO;
import com.hongshu.dto.ReplyCountDTO;
import com.hongshu.dto.ReplyDTO;
import com.hongshu.entity.Comment;
import com.hongshu.vo.ConditionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Mapper
public interface CommentDao extends BaseMapper<Comment>
{

    /**
     * 评论列表
     * 查看评论
     *
     * @param blogId 文章id
     * @param current   当前页码
     * @param size      大小
     * @return 评论集合
     */
    List<CommentDTO> listComments(@Param("current") Long current, @Param("size") Long size, @Param("blogId") Integer blogId);

    /**
     * 查看评论id集合下的回复
     *
     * @param commentIdList 评论id集合
     * @return 回复集合
     */
    List<ReplyDTO> listReplies(@Param("commentIdList") List<Integer> commentIdList);

    /**
     * 查看当条评论下的回复
     *
     * @param commentId 评论id
     * @param current   当前页码
     * @param size      大小
     * @return 回复集合
     */
    List<ReplyDTO> listRepliesByCommentId(@Param("current") Long current, @Param("size") Long size, @Param("commentId") Integer commentId);

    /**
     * 根据评论id查询回复总量
     *
     * @param commentIdList 评论id集合
     * @return 回复数量
     */
    List<ReplyCountDTO> listReplyCountByCommentId(@Param("commentIdList") List<Integer> commentIdList);

    /**
     * 查询后台评论
     *
     * @param current   页码
     * @param size      大小
     * @param condition 条件
     * @return 评论集合
     */
    List<CommentBackDTO> listCommentBackDTO(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 统计后台评论数量
     *
     * @param condition 条件
     * @return 评论数量
     */
    Integer countCommentDTO(@Param("condition") ConditionVO condition);
}
