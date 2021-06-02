package cn.hellozuofeng.nacosdemo.server.impl;

import cn.hellozuofeng.nacosdemo.server.ProviderServer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 16:11
 * @Description:
 */
@Service
public class ProviderServerImpl implements ProviderServer {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String echo(String msg) {
        return restTemplate.getForObject("http://nacos-provider/test/echo/" + msg, String.class);
    }
}