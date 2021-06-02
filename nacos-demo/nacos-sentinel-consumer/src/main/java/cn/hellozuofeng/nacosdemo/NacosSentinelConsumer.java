package cn.hellozuofeng.nacosdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 15:14
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosSentinelConsumer {
    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelConsumer.class, args);
    }
}
