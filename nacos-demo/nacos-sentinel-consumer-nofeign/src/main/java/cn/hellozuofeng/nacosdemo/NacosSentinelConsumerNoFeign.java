package cn.hellozuofeng.nacosdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 16:05
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosSentinelConsumerNoFeign {

    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelConsumerNoFeign.class, args);
    }
}
