package cn.riverwind.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


@Data//get set
@TableName("chat_friends")
@ApiModel("好友关系")
public class ChatFriends {
    @TableId
    private Integer id;

    private String userid;

    private String fuserid;

    private Date addtime;

}