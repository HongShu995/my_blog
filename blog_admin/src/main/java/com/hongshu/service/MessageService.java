package com.hongshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongshu.dto.MessageBackDTO;
import com.hongshu.dto.MessageDTO;
import com.hongshu.entity.Message;
import com.hongshu.vo.ConditionVO;
import com.hongshu.vo.MessageVO;
import com.hongshu.vo.PageResult;
import com.hongshu.vo.ReviewVO;

import java.util.List;

/**
 * 留言服务
 *
 * @author Hong_Shu995
 * @date 2022-01-01
 */
public interface MessageService extends IService<Message>
{

    /**
     * 添加留言弹幕
     *
     * @param messageVO 留言对象
     */
    void saveMessage(MessageVO messageVO);

    /**
     * 查看留言弹幕
     *
     * @return 留言列表
     */
    List<MessageDTO> listMessages();

    /**
     * 审核留言
     *
     * @param reviewVO 审查签证官
     */
    void updateMessagesReview(ReviewVO reviewVO);

    /**
     * 查看后台留言
     *
     * @param condition 条件
     * @return 留言列表
     */
    PageResult<MessageBackDTO> listMessageBackDTO(ConditionVO condition);

}
