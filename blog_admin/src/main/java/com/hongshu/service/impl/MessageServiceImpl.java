package com.hongshu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongshu.dao.MessageDao;
import com.hongshu.dto.MessageBackDTO;
import com.hongshu.dto.MessageDTO;
import com.hongshu.entity.Message;
import com.hongshu.service.BlogInfoService;
import com.hongshu.service.MessageService;
import com.hongshu.util.BeanCopyUtils;
import com.hongshu.util.IpUtils;
import com.hongshu.util.PageUtils;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.MessageVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.hongshu.constant.CommonConstant.FALSE;
import static com.hongshu.constant.CommonConstant.TRUE;
/**
 * 类名
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService
{
    @Autowired
    private MessageDao messageDao;

    @Resource
    private HttpServletRequest request;

    @Autowired
    private BlogInfoService blogInfoService;

    @Override
    public void saveMessage(MessageVO messageVO)
    {
        // 判断是否需要审核
        Integer isReview = blogInfoService.getWebsiteConfig().getIsMessageReview();
        // 获取用户ip
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        Message message = BeanCopyUtils.copyObject(messageVO, Message.class);
        message.setIpAddress(ipAddress);
        message.setIsReview(isReview == TRUE ? FALSE : TRUE);
        message.setIpSource(ipSource);
        messageDao.insert(message);
    }

    @Override
    public List<MessageDTO> listMessages()
    {
        // 查询留言列表
        List<Message> messageList = messageDao.selectList(new LambdaQueryWrapper<Message>()
                .select(Message::getId, Message::getNickname, Message::getAvatar, Message::getMessageContent, Message::getTime)
                .eq(Message::getIsReview, TRUE));
        return BeanCopyUtils.copyList(messageList, MessageDTO.class);
    }

    @Override
    public void updateMessagesReview(ReviewVO reviewVO)
    {
        // 修改留言审核状态
        List<Message> messageList = reviewVO.getIdList().stream().map(item -> Message.builder()
                        .id(item)
                        .isReview(reviewVO.getIsReview())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(messageList);
    }

    @Override
    public PageResult<MessageBackDTO> listMessageBackDTO(ConditionVO condition)
    {
        // 分页查询留言列表
        Page<Message> page = new Page<>(PageUtils.getCurrent(), PageUtils.getSize());
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<Message>()
                .like(StringUtils.isNotBlank(condition.getKeywords()), Message::getNickname, condition.getKeywords())
                .eq(Objects.nonNull(condition.getIsReview()), Message::getIsReview, condition.getIsReview())
                .orderByDesc(Message::getId);
        Page<Message> messagePage = messageDao.selectPage(page, messageLambdaQueryWrapper);
        // 转换DTO
        List<MessageBackDTO> messageBackDTOList = BeanCopyUtils.copyList(messagePage.getRecords(), MessageBackDTO.class);
        return new PageResult<>(messageBackDTOList, (int) messagePage.getTotal());
    }
}
