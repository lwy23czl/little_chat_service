package cn.riverwind.service;

import cn.riverwind.model.dto.LoginDto;
import cn.riverwind.model.entity.Userinfo;

/**
 * 登录服务
 */
public interface LoginService {
    boolean login(LoginDto loginDto);

    boolean register(Userinfo userinfo);
}
