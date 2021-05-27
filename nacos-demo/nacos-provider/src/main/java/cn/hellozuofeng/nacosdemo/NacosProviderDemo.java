package cn.hellozuofeng.nacosdemo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/27 11:59
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
public class NacosProviderDemo {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderDemo.class, args);
    }
}
