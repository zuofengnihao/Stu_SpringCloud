package cn.hellozuofeng.nacosdemo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/27 13:53
 * @Description:
 */
@FeignClient(value = "nacos-provider", path = "/provider")
public interface ProviderClient {

    @GetMapping("/echo/{msg}")
    String echo(@PathVariable("msg") String msg);
}
