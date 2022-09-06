package cn.riverwind.mapper;

import cn.riverwind.model.entity.ChatMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMsgMapper extends BaseMapper<ChatMsg> {
}
