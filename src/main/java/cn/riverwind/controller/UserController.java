package cn.riverwind.controller;

import cn.riverwind.model.vo.BusinessCardVo;
import cn.riverwind.result.ResponseResult;
import cn.riverwind.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "用户信息接口")
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryCard/{username}")
    @ApiOperation("查询用户名片信息")
    public ResponseResult queryUserBusinessCard(@PathVariable("username") String username){
        BusinessCardVo vo = userService.queryUserBusinessCard(username);
        if (vo!=null){
            return new ResponseResult(vo);
        }
        return new ResponseResult<>(false,"用户不存在");
    }


    @GetMapping("/queryname/{username}")
    @ApiOperation("查询用户名是否已存在")
    public ResponseResult isUsername(@PathVariable("username") String username){
        if (userService.isUsername(username)){
            //用户名存在
            return new ResponseResult<>(false,"用户名已存在");
        }
        //用户名不存在
        return new ResponseResult();
    }
}
