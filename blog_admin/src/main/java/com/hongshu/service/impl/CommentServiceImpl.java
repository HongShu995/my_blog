package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.BlogDao;
import com.hongshu.dao.CommentDao;
import com.hongshu.dao.UserInfoDao;
import com.hongshu.dto.CommentBackDTO;
import com.hongshu.dto.CommentDTO;
import com.hongshu.dto.ReplyCountDTO;
import com.hongshu.dto.ReplyDTO;
import com.hongshu.entity.Comment;
import com.hongshu.service.BlogInfoService;
import com.hongshu.service.CommentService;
import com.hongshu.util.HTMLUtils;
import com.hongshu.util.PageUtils;
import com.hongshu.util.UserUtil;
import com.hongshu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hongshu.constant.CommonConstant.*;
/**
 * 评论服务实例
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService
{
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private BlogInfoService blogInfoService;

    /**
     * 网站网址
     */
    @Value("${website.url}")
    private String websiteUrl;

    @Override
    public PageResult<CommentDTO> listComments(Integer blogId)
    {
        // 查询文章评论量
        Long commentCount = commentDao.selectCount(new LambdaQueryWrapper<Comment>()
                .eq(Objects.nonNull(blogId), Comment::getBlogId, blogId)
                .isNull(Objects.isNull(blogId), Comment::getBlogId)
                .isNull(Comment::getParentId)
                .eq(Comment::getIsReview, TRUE));
        if (commentCount == 0)
        {
            return new PageResult<>();
        }
        // 分页查询评论集合
        List<CommentDTO> commentDTOList = commentDao.listComments(PageUtils.getLimitCurrent(), PageUtils.getSize(), blogId);
        if (CollectionUtils.isEmpty(commentDTOList))
        {
            return new PageResult<>();
        }
        // 查询redis的评论点赞数据
        //Map<String, Object> likeCountMap = redisService.hGetAll(COMMENT_LIKE_COUNT);
        // 提取评论id集合
        List<Integer> commentIdList = commentDTOList.stream()
                .map(CommentDTO::getId)
                .collect(Collectors.toList());
        // 根据评论id集合查询回复数据
        List<ReplyDTO> replyDTOList = commentDao.listReplies(commentIdList);
        // 封装回复点赞量
        //replyDTOList.forEach(item -> item.setLikeCount((Integer) likeCountMap.get(item.getId().toString())));
        // 根据评论id分组回复数据
        Map<Integer, List<ReplyDTO>> replyMap = replyDTOList.stream()
                .collect(Collectors.groupingBy(ReplyDTO::getParentId));
        // 根据评论id查询回复量
        Map<Integer, Integer> replyCountMap = commentDao.listReplyCountByCommentId(commentIdList)
                .stream().collect(Collectors.toMap(ReplyCountDTO::getCommentId, ReplyCountDTO::getReplyCount));
        // 封装评论数据
        commentDTOList.forEach(item ->
        {
            //item.setLikeCount((Integer) likeCountMap.get(item.getId().toString()));
            item.setReplyDTOList(replyMap.get(item.getId()));
            item.setReplyCount(replyCountMap.get(item.getId()));
        });
        return new PageResult<>(commentDTOList, commentCount);
    }

    @Override
    public List<ReplyDTO> listRepliesByCommentId(Integer commentId)
    {
        // 转换页码查询评论下的回复
        List<ReplyDTO> replyDTOList = commentDao.listRepliesByCommentId(PageUtils.getLimitCurrent(), PageUtils.getSize(), commentId);
        // 查询redis的评论点赞数据
        //Map<String, Object> likeCountMap = redisService.hGetAll(COMMENT_LIKE_COUNT);
        // 封装点赞数据
        //replyDTOList.forEach(item -> item.setLikeCount((Integer) likeCountMap.get(item.getId().toString())));
        return replyDTOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(CommentVO commentVO)
    {
        // 判断是否需要审核
        WebsiteConfigVO websiteConfig = blogInfoService.getWebsiteConfig();
        Integer isReview = websiteConfig.getIsCommentReview();
        // 过滤标签
        commentVO.setCommentContent(HTMLUtils.deleteTag(commentVO.getCommentContent()));
        Comment comment = Comment.builder()
                .userId(UserUtil.getLoginUser().getUserInfoId())
                .replyUserId(commentVO.getReplyUserId())
                .blogId(commentVO.getBlogId())
                .commentContent(commentVO.getCommentContent())
                .parentId(commentVO.getParentId())
                .isReview(isReview == TRUE ? FALSE : TRUE)
                .build();
        commentDao.insert(comment);
        // 判断是否开启邮箱通知,通知用户
        if (websiteConfig.getIsEmailNotice().equals(TRUE))
        {
            notice(comment);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCommentLike(Integer commentId)
    {
        //// 判断是否点赞
        //String commentLikeKey = COMMENT_USER_LIKE + UserUtils.getLoginUser().getUserInfoId();
        //if (redisService.sIsMember(commentLikeKey, commentId)) {
        //    // 点过赞则删除评论id
        //    redisService.sRemove(commentLikeKey, commentId);
        //    // 评论点赞量-1
        //    redisService.hDecr(COMMENT_LIKE_COUNT, commentId.toString(), 1L);
        //} else {
        //    // 未点赞则增加评论id
        //    redisService.sAdd(commentLikeKey, commentId);
        //    // 评论点赞量+1
        //    redisService.hIncr(COMMENT_LIKE_COUNT, commentId.toString(), 1L);
        //}
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCommentsReview(ReviewVO reviewVO)
    {
        // 修改评论审核状态
        List<Comment> commentList = reviewVO.getIdList().stream().map(item -> Comment.builder()
                        .id(item)
                        .isReview(reviewVO.getIsReview())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(commentList);
    }

    @Override
    public PageResult<CommentBackDTO> listCommentBackDTO(ConditionVO condition)
    {
        // 统计后台评论量
        Integer count = commentDao.countCommentDTO(condition);
        if (count == 0)
        {
            return new PageResult<>();
        }
        // 查询后台评论集合
        List<CommentBackDTO> commentBackDTOList = commentDao.listCommentBackDTO(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(commentBackDTOList, count);
    }

    /**
     * 通知评论用户
     *
     * @param comment 评论信息
     */
    @Async
    public void notice(Comment comment)
    {
        // 查询回复用户邮箱号
        Integer authorId;
        if (Objects.isNull(comment.getBlogId()))
        {
            authorId = BLOGGER_ID;
        } else {
            authorId = blogDao.selectById(comment.getBlogId()).getUserId();
        }
        Integer userId = Optional.ofNullable(comment.getReplyUserId()).orElse(authorId);
        String email = userInfoDao.selectById(userId).getEmail();
        if (StringUtils.isNotBlank(email))
        {
            // 发送消息
            //EmailDTO emailDTO = new EmailDTO();
            //if (comment.getIsReview().equals(TRUE)) {
            //    // 评论提醒
            //    emailDTO.setEmail(email);
            //    emailDTO.setSubject("评论提醒");
            //    // 判断页面路径
            //    String url = Objects.nonNull(comment.getBlogId()) ? websiteUrl + BLOG_PATH + comment.getBlogId() : websiteUrl + LINK_PATH;
            //    emailDTO.setContent("您收到了一条新的回复，请前往" + url + "\n页面查看");
            //} else {
            //    // 管理员审核提醒
            //    String adminEmail = userInfoDao.selectById(authorId).getEmail();
            //    emailDTO.setEmail(adminEmail);
            //    emailDTO.setSubject("审核提醒");
            //    emailDTO.setContent("您收到了一条新的回复，请前往后台管理页面审核");
            //}
            //rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        }
    }
}
