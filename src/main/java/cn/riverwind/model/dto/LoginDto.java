package cn.riverwind.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 登录前端传入参数
 */
@Data
@ApiModel("登录")
public class LoginDto {
    private String username;
    private String password;
}
