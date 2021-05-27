package cn.hellozuofeng.nacosdemo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/27 12:01
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2Doc
public class NacosConsumerDemo {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerDemo.class, args);
    }
}
