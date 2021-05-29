# Stu_SpringCloud

## 技术选型

* 注册服务中心
  - Eureka ×  
    - pom文件引入
    ```java
    <!-- 服务端 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    <!-- 客户端 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    ```
    - applicaiton.yml配置
    服务端
    ```java
    eureka:
      instance:
        ## 配置实例名称
        hostname: localhost
      client:
        ## 是否注册到eureka上
        register-with-eureka: false
        ## 检索服务 是否从Eureka Server获取注册信息，默认为true
        fetch-registry: false
        service-url:
          # 集群配置
          #defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
          # 单机配置
          defaulZone: http://${eureka.instance.hostname}:${server.port}/eureka/
        server:
          ## 当eureka启动时，不能从集群节点中获取到instance注册信息，应该等待的时间
          wait-time-in-ms-when-sync-empty: 0
          ## 是否开启自我保护机制
          enable-self-preservation: true
          ### eureka多长时间更新一次数据
          peer-eureka-nodes-update-interval-ms: 100000
    ```
    客户端
    ```java
    spring:
      application:
        name: cloud-payment-service
    eureka:
      client:
        register-with-eureka: true
        fetch-registry: true
        service-url:
          #集群
          #defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
          #单机
          defaultZone: http://localhost:7001/eureka/
      instance:
        instance-id: payment8001
        prefer-ip-address: true
    ```
    - 代码
    ```java
    // 客户端
    @EnableEurekaServer
    // 服务端
    @EnableDiscoveryClient
    ```

  - Zookeeper √
  - Consul √
  - Nacos √

  
  | 组件名 | 语言 | CAP | 将康服务检查 | 对外暴露接口 | SpringCloud集成 |
  | ----- | ----- | ----- | ----- | ----- | ----- |
  | Eureka | Java | AP | 可配置支持 | HTTP | 已集成 |
  | Consul | Go | CP | 支持 | HTTP/DNS | 已集成 |
  | ZooKeeper | Java | CP | 支持 | 客户端 | 已集成 |


* 调用服务
  - Ribbon √
  - LoadBalance √

* 调用服务2
  - Feign ×
  - OpenFeign √
    - pom文件
      ```java
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-openfeign</artifactId>
      </dependency>
      ```
    - application.yml配置
    ```java
    # Feign详细日志（需要配置日志bean）
    logging:
      level:
        #或包名加服务名称cn.hellozuofeng.springcloud.service.PaymentService: debug
        cn:
          hellozuofeng:
            springcloud:
              service:
                PaymentService: debug
    ```
    - 代码
    ```java
    // 1
    @EnableFeignClients

    //2 
    @FeignClinet(name = "spring.applicaiton.name")

    // 日志配置
    @Configuration
    public class FeignConfig {

        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
    }


    ```
* 服务降级
  - Hystrix ×
    
    

  - resilience4j √
  - sentienl √

* 服务网关
  - Zuul ×
  - Zuul2 ×
  - gateway √

* 服务配置
  - Config ×
  - Nacos √

* 服务总线
  - Bus ×
  - Nacos √

## Hystrix

* 服务降级
* 服务熔断
* 服务限流

