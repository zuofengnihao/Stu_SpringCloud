package cn.hellozuofeng.nacosdemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/27 13:53
 * @Description:
 */
@FeignClient(value = "test-nacos-provider-sever")
public interface ProviderClient {

    @GetMapping("/test/echo/{msg}")
    public String echo(@PathVariable("msg") String msg);
}
