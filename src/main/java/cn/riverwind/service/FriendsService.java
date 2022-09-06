package cn.riverwind.service;

import cn.riverwind.model.entity.ChatFriends;

public interface FriendsService {
    boolean addFriends(ChatFriends chatFriends);

    boolean deleteFriends(ChatFriends chatFriends);

}
