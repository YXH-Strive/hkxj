package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author YXH
 * @date 2020/2/12 - 21:44
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class WechatOpenidServiceTest {
    @Resource
    private WechatOpenidService wechatOpenidService;

    @Test
    public void insertTest(){
        System.out.println("==================================");
        wechatOpenidService.insertByWechatOpenid();
        System.out.println("==================================");
    }
}
