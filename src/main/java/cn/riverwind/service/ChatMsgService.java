package cn.riverwind.service;

import cn.riverwind.model.dto.ViewMsgDto;
import cn.riverwind.model.entity.ChatMsg;

import java.util.List;

public interface ChatMsgService {

    //插入一条聊天记录
    void insertChatRecord(ChatMsg chatMsg);

    //根据聊天双方获取聊天记录
    List<ChatMsg> viewChatRecords(ViewMsgDto viewMsgDto);

}
