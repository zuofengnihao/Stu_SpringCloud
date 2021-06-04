package cn.hellozuofeng.nacosdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/4 10:39
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDemo {
    public static void main(String[] args) {
        SpringApplication.run(GatewayDemo.class, args);
    }
}
