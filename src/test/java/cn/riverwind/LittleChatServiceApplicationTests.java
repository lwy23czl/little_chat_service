package cn.riverwind;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@Slf4j
class LittleChatServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
//        String simpleUUID = IdUtil.simpleUUID();
//        log.info(simpleUUID);/
//        String s = RandomUtil.randomNumbers(10);
        String s = RandomUtil.randomString(10);
        log.info(s);
    }

}
