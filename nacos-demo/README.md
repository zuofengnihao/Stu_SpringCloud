# Nacos源码阅读

-----------------------

## 注册服务

```java
// 需要注册到nacos上的服务名称
String serviceName = "helloworld.service";
//根据已开启的nacos服务地址反射创建命名服务实例
//NacosFactory可以创建三种服务实例，此处创建的是命名服务实例（下面补充说明）
NamingService namingService = NacosFactory.createNamingService("192.168.3.72:8848");
namingService.registerInstance(serviceName, "192.168.3.72", 8001);
TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
```

`NacosFactory`提供三个服务类型实例创建。
```java
//　代码是省略的　具体方法请自行查看NacosFactory类
public class NacosFactory {
    // 配置服务
    public static ConfigService createConfigService;
    // 命名服务
    public static NamingService createNamingService;
    // 维持服务
    public static NamingMaintainService createMaintainService;
}
```
以上三种服务又对应三个服务实例工厂，服务工厂对应三个服务类接口，服务类接口又对应三个默认实现类。
```java
//　代码是省略的 具体内容自行查看
// 配置服务工厂
public class ConfigFactory {
    public static ConfigService createConfigService;
}
// 配置服务接口
public interface ConfigService {}
// 配置服务默认实现类
public class NacosConfigService implements ConfigService {}


// 命名服务工厂
public class NamingFactory {
    public static NamingService createNamingService;
}
// 命名服务接口
public interface NamingService {}
// 命名服务默认实现类
public class NacosNamingService implements NamingService {}


// 维护服务工厂
public class NamingMaintainFactory {
    public static NamingMaintainService createMaintainService;
}
// 维护服务接口
public interface NamingMaintainService {}
// 维护服务默认实现类
public class NacosNamingMaintainService implements NamingMaintainService {}

```
自然，`NacosFactory.createNamingService()`方法中，则是调用`NamingService createNamingService()`方法。
```java
public static NamingService createNamingService(String serverAddr) throws NacosException {
    return NamingFactory.createNamingService(serverAddr);
}
```
进入`NamingFactory.createNamingService()`方法中。
```java
public static NamingService createNamingService(String serverList) throws NacosException {
    // 反射调用 NacosNamingService 构造[String类型有参构造 NacosNamingService(String serverList)]创建NamingService实例 并返回
    try {
        Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.naming.NacosNamingService");
        Constructor constructor = driverImplClass.getConstructor(String.class);
        return (NamingService) constructor.newInstance(serverList);
    } catch (Throwable e) {
        throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
    }
}
```
直接进入`acosNamingService(String serverList)`构造方法中。
```java
public NacosNamingService(String serverList) throws NacosException {
    // 创建properties对象
    Properties properties = new Properties();
    // 设置值 key=serverAddr value=入参（如 127.0.0.1:8848）
    properties.setProperty(PropertyKeyConst.SERVER_ADDR, serverList);
    // 初始化方法
    init(properties);
}
```
进入初始化方法。
```java

```