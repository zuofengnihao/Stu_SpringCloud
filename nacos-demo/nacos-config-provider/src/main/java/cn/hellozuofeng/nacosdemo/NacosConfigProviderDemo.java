package cn.hellozuofeng.nacosdemo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 10:09
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
public class NacosConfigProviderDemo {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigProviderDemo.class, args);
        /*while (true) {
            String myName = applicationContext.getEnvironment().getProperty("myName");
            System.out.println(myName);
            TimeUnit.SECONDS.sleep(1);
        }*/
    }
}
