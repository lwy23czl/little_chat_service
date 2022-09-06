package cn.riverwind.controller;

import cn.riverwind.model.entity.ChatFriends;
import cn.riverwind.result.ResponseResult;
import cn.riverwind.service.FriendsService;
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
@Api(tags = "好友接口")
@RequestMapping("/friends")
public class FriendsController {


    @Autowired
    private FriendsService friendsService;

    @PostMapping("/add")
    @ApiOperation("添加好友")
    public ResponseResult add(@RequestBody ChatFriends chatFriends){
        if (friendsService.addFriends(chatFriends)){
            return  new ResponseResult(true,"添加成功");
        }
        return new ResponseResult(false,"添加失败");
    }


    @PostMapping("/delete")
    @ApiOperation("删除好友")
    public ResponseResult delete(@RequestBody ChatFriends chatFriends){
        if (friendsService.deleteFriends(chatFriends)){
            return new ResponseResult(true,"删除成功");
        }
        return new ResponseResult(false,"删除失败");
    }
}
