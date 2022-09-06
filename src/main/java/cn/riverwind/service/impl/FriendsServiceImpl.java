package cn.riverwind.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.riverwind.mapper.ChatFriendsMapper;
import cn.riverwind.model.entity.ChatFriends;
import cn.riverwind.service.FriendsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private ChatFriendsMapper chatFriendsMapper;
    @Override
    public boolean addFriends(ChatFriends chatFriends) {
        //设置添加好友的时间
        chatFriends.setAddtime(DateUtil.parse(DateUtil.now()));
        int insert = chatFriendsMapper.insert(chatFriends);
        return insert>0?true:false;
    }

    @Override
    public boolean deleteFriends(ChatFriends chatFriends) {
        LambdaQueryWrapper<ChatFriends> qw = new LambdaQueryWrapper<>();
        qw.in(ChatFriends::getFuserid,chatFriends.getFuserid())
                .in(ChatFriends::getUserid,chatFriends.getUserid());
        int delete = chatFriendsMapper.delete(qw);
        return delete>0?true:false;
    }
}
