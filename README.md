# Stu_SpringCloud

## 技术选型

* 注册服务中心
  - Eureka ×
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

## 建立父工程

