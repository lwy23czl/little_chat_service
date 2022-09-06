package cn.riverwind.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.riverwind.mapper.ChatMsgMapper;
import cn.riverwind.model.dto.ViewMsgDto;
import cn.riverwind.model.entity.ChatMsg;
import cn.riverwind.service.ChatMsgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMsgServiceImpl implements ChatMsgService {

    @Autowired
    private ChatMsgMapper chatMsgMapper;

    /**
     * 插入聊天记录
     * @param chatMsg
     */
    @Override
//    @Transactional
    public void insertChatRecord(ChatMsg chatMsg) {
        //设置发送时间
        chatMsg.setSendtime(DateUtil.parse(DateUtil.now()));
        chatMsgMapper.insert(chatMsg);
    }

    /**
     * 查询聊天记录
     * @param viewMsgDto
     * @return
     */
    @Override
    public List<ChatMsg> viewChatRecords(ViewMsgDto viewMsgDto) {
        LambdaQueryWrapper<ChatMsg> qw = new LambdaQueryWrapper<>();
        qw.in(ChatMsg::getReciveuserid,viewMsgDto.getReciveuserid())
                .in(ChatMsg::getSenduserid,viewMsgDto.getSenduserid())
                .or((w)->{
                    w.in(ChatMsg::getReciveuserid,viewMsgDto.getSenduserid())
                            .in(ChatMsg::getSenduserid,viewMsgDto.getReciveuserid());
                });
        return chatMsgMapper.selectList(qw);
    }
}
