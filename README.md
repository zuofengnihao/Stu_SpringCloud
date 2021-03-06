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
    - 服务降级
      - pom文件
      ```java
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
      </dependency>
      ```
      - application.yml
      ```java
      # feign消费端配置
      feign:
        hystrix:
          enabled: true
      ```
      - 代码
      服务端（单个降级）
      ```java
      // 1.
      @EnableCircuitBreaker
      // 2.
      public Payment fallbackMethod(Long id) {
          log.info("getPaymentById 服务发生降级！");
          return null;
      }
      // 3.
      @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
              @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
      })
      public Payment getPaymentById(Long id) {
          // 抛出异常
          //int i = 1/0;
          try {
              TimeUnit.SECONDS.sleep(3);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return paymentDao.getPaymentById(id);
      }
      ```
      消费端（全局降级）
      ```java
      // 1.
      @EnableHystrix
      // 2.
      public CommonResult<Payment> globalFallbackMethod() {
          return new CommonResult<Payment>(444, "服务超时或出现异常（Global）");
      }
      // 3.
      @DefaultProperties(defaultFallback = "globalFallbackMethod")
      public class OrderClient {}
      // 4.
      @HystrixCommand
      @GetMapping("/consumer/payment/get/{id}")
      public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        //抛出异常
        int i = 1 / 0;
        return paymentService.getPaymentById(id);
      }
      ```
      消费端（Feign配置降级，前提配置yml）
      ```java
      // 1. 前提配置yml，上面有
      @EnableHystrix
      // 2. 实现client服务接口
      @Component
      public class PaymentServiceImpl implements PaymentService {


          public CommonResult create(Payment payment) {
              return new CommonResult(444, "create fallback");
          }

          public CommonResult getPaymentById(Long id) {
              return new CommonResult(444, "getPaymentById fallback");
          }

          public CommonResult deletePaymentById(Long id) {
              return new CommonResult(444, "deletePaymentById fallback");
          }

          public CommonResult updatePaymentById(Payment payment) {
              return new CommonResult(444, "updatePaymentById fallback");
          }

          public Object discovery() {
              return new CommonResult(444, "discovery fallback");
          }
      }
      //3. 
      @FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentServiceImpl.class)
      @Component
      public interface PaymentService {
        @PostMapping("/payment/create")
        CommonResult create(@RequestBody Payment payment);

        @GetMapping("/payment/get/{id}")
        CommonResult getPaymentById(@PathVariable("id") Long id);

        @PostMapping("payment/delete/{id}")
        CommonResult deletePaymentById(@PathVariable("id") Long id);

        @PostMapping("payment/update")
        CommonResult updatePaymentById(@RequestBody Payment payment);

        @GetMapping("/payment/discovery")
        Object discovery();
      }
      ```
    - 服务熔断
      - 代码
      ```java
      @HystrixCommand(fallbackMethod = "deletePaymentByIdHandler", commandProperties = {
              @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
              @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
              @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期
              @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
      })
      public int deletePaymentById(Long id) {
          if (id < 0) {
              throw new RuntimeException("id 不能为负数");
          }
          return paymentDao.deletePaymentById(id);
      }

      public int deletePaymentByIdHandler(Long id) {
          return -1;
      }
      ```
    - dashboard
    pom引入
    ```java
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    </dependency>
    ```
    代码
    ```java
    @EnableHystrixDashboard
    ```
  - resilience4j √
  - sentienl √

* 服务网关
  - Zuul ×
  - Zuul2 ×
  - gateway √  
    创建项目pom引入
    ```java
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    ```
    网关在application.yml中配置
    ```java
    server:
      port: 9001

    spring:
      application:
        name: cloud-gateway
      cloud:
        gateway:
          discovery:
            locator:
              enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
          routes:
            - id: payment8001_get_routh
              #uri: http://localhost:8001
              uri: lb://CLOUD-PAYMENT-SERVICE
              predicates:
                - Path=/payment/get/**
            - id: payment8001_delete_routh
              #uri: http://localhost:8001
              uri: lb://CLOUD-PAYMENT-SERVICE
              predicates:
                - Path=/payment/delete/**

    eureka:
      client:
        register-with-eureka: true
        fetch-registry: true
        service-url:
          #defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
          defaultZone: http://localhost:7001/eureka/
      instance:
        instance-id: gateway9001
    ```
    网关在编码中配置
    ```java
    @Configuration
    public class GatewayConfig {

        @Bean
        public RouteLocator routeLocator(RouteLocatorBuilder builder) {
            RouteLocatorBuilder.Builder routes = builder.routes();
            routes.route("test_routh", r -> r.path("/guonei").uri("https://news.baidu.com/guonei")).build();
            return routes.build();
        }
    }
    ```
    多种断言请百度。  

    GatewayFilter
    ```java
    @Component
    public class MyGatewayFilter implements GlobalFilter, Ordered {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            String name = exchange.getRequest().getQueryParams().getFirst("name");
            if (name == null) {
                // 拦截
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }
            // 放行
            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return 0;
        }
    }
    ```


* 服务配置
  - Config ×  
    pom文件
    ```java
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
    ```
  - Nacos √

* 服务总线
  - Bus ×
  - Nacos √