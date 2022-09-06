package cn.riverwind.service;

import cn.riverwind.model.vo.BusinessCardVo;

public interface UserService {
    boolean isUsername(String username);

    BusinessCardVo queryUserBusinessCard(String username);
}
