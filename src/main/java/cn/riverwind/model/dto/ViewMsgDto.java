package cn.riverwind.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("查看聊天记录")
public class ViewMsgDto {
    private String senduserid;
    private String reciveuserid;
}
