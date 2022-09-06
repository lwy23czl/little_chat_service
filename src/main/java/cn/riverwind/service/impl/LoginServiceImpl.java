package cn.riverwind.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.riverwind.mapper.UserinfoMapper;
import cn.riverwind.model.dto.LoginDto;
import cn.riverwind.model.entity.Userinfo;
import cn.riverwind.service.LoginService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Override
    public boolean login(LoginDto loginDto) {
        //将输入的密码进行md5加密
        String ps = DigestUtil.md5Hex(loginDto.getPassword());
        LambdaQueryWrapper<Userinfo> qw = new LambdaQueryWrapper<>();
        qw.in(Userinfo::getUsername,loginDto.getUsername())
                .in(Userinfo::getPassword,ps);
        return userinfoMapper.equals(qw);
    }

    @Override
    public boolean register(Userinfo userinfo) {
        //为用户设置唯一id
        userinfo.setUserid(RandomUtil.randomString(10));
        //将密码加密
        userinfo.setPassword(DigestUtil.md5Hex(userinfo.getPassword()));
        //向表中插入数据
        int insert = userinfoMapper.insert(userinfo);
        return insert>0?true:false;
    }
}
