package cn.riverwind.controller;

import cn.riverwind.model.dto.ViewMsgDto;
import cn.riverwind.model.entity.ChatMsg;
import cn.riverwind.result.ResponseResult;
import cn.riverwind.service.ChatMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "聊天记录接口")
@RequestMapping("/msg")
public class ChatMsgController {
    @Autowired
    private ChatMsgService chatMsgService;

    @PostMapping("/view/")
    @ApiOperation("查看聊天记录")
    public ResponseResult viewChatRecords(@RequestBody ViewMsgDto viewMsgDto){
        List<ChatMsg> chatMsgs = chatMsgService.viewChatRecords(viewMsgDto);
        return new ResponseResult(chatMsgs);
    }

}
