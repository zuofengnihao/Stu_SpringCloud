package cn.hellozuofeng.nacosdemo.service.impl;

import cn.hellozuofeng.nacosdemo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/28 10:57
 * @Description:
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String echo(String msg) {
        return "nacos-sentinel-provider echo : " + msg;
    }
}
