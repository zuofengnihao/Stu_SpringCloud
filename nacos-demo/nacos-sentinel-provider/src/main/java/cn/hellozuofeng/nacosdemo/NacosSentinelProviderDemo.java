package cn.hellozuofeng.nacosdemo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/28 09:35
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
public class NacosSentinelProviderDemo {
    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelProviderDemo.class, args);
    }
}
