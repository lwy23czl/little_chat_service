package cn.riverwind.controller;

import cn.hutool.core.util.RandomUtil;
import cn.riverwind.model.dto.LoginDto;
import cn.riverwind.model.entity.Userinfo;
import cn.riverwind.result.ResponseResult;
import cn.riverwind.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "用户登录、注册接口")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    @ApiOperation("登录")
    public ResponseResult login(@RequestBody LoginDto loginDto){
        if (loginService.login(loginDto)){
            //账号密码验证成功
            return new ResponseResult();
        }
        return new ResponseResult<>(false,"用户或密码不正确");
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public ResponseResult register(@RequestBody Userinfo userinfo){
        if (loginService.register(userinfo)){
            //注册成功
            return new ResponseResult<>();
        }
        return new ResponseResult<>(false,"系统繁忙，请稍后");

    }


}
