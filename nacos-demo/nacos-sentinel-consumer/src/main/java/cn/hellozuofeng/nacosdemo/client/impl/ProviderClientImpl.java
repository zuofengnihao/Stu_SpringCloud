package cn.hellozuofeng.nacosdemo.client.impl;

import cn.hellozuofeng.nacosdemo.client.ProviderClient;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 15:49
 * @Description:
 */
@Component
public class ProviderClientImpl implements ProviderClient {
    @Override
    public String echo(String msg) {
        return "error server fallback";
    }
}
