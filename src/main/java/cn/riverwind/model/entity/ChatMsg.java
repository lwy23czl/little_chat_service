package cn.riverwind.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@TableName("chat_msg")
@ApiModel("聊天记录")
public class ChatMsg {
    private String senduserid;

    private String reciveuserid;

    private Date sendtime;

    private String sendtext;
}