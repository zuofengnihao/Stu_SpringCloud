package cn.hellozuofeng.nacosdemo.client;

import cn.hellozuofeng.nacosdemo.client.impl.ProviderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 15:18
 * @Description:
 */
@FeignClient(value = "nacos-provider", fallback = ProviderClientImpl.class)
public interface ProviderClient {

    @GetMapping("/test/echo/{msg}")
    String echo(@PathVariable("msg") String msg);
}
