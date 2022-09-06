package cn.riverwind.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户名片")
public class BusinessCardVo {
    private String nickname;
    private String sign;
    private String img;
    private String username;
    private String sex;
}
