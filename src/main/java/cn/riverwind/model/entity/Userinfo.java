package cn.riverwind.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@TableName("userinfo")
@ApiModel("用户信息")
public class Userinfo {
    @TableId
    private Integer uid;
    private String nickname;
    private String sign;
    private String userid;
    private String img;
    private String username;
    private String password;
    private String sex;
}
