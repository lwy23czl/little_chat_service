package cn.riverwind.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.riverwind.mapper.UserinfoMapper;
import cn.riverwind.model.entity.Userinfo;
import cn.riverwind.model.vo.BusinessCardVo;
import cn.riverwind.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @Override
    public boolean isUsername(String username) {
        LambdaQueryWrapper<Userinfo> qw = new LambdaQueryWrapper<>();
        qw.in(Userinfo::getUsername,username);
        return userinfoMapper.equals(qw);
    }

    /**
     * 根据用户名查询用户的卡片信息
     * @param username
     * @return
     */
    @Override
    public BusinessCardVo queryUserBusinessCard(String username) {
        LambdaQueryWrapper<Userinfo> qw = new LambdaQueryWrapper<>();
        qw.in(Userinfo::getUsername,username);
        BusinessCardVo businessCardVo = BeanUtil.copyProperties(userinfoMapper.selectOne(qw), BusinessCardVo.class);
        return businessCardVo;
    }


}
