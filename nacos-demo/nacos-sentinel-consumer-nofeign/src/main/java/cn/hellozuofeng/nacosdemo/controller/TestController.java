package cn.hellozuofeng.nacosdemo.controller;

import cn.hellozuofeng.nacosdemo.server.ProviderServer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 16:14
 * @Description:
 */
@Api(tags = "消费者测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    public static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ProviderServer providerServer;

    @ApiOperation(value = "消费者回音测试")
    @GetMapping("/echo/{msg}")
    @SentinelResource( value = "echo", blockHandler = "echoBlockHandler", fallback = "echoFallback")
    public String echo(@PathVariable("msg") String msg) {
        return "consumer forward : [ " + providerServer.echo(msg) + " ]";
    }

    // 服务流量控制处理，参数最后多一个 BlockException，其余与原函数一致。
    public String echoBlockHandler(String msg, BlockException blockException) {
        // Do some log here.
        blockException.printStackTrace();
        return "consumer block handler forward : [ " + providerServer.echo(msg) + " ]";
    }

    // 服务熔断降级处理，函数签名与原函数一致或加一个 Throwable 类型的参数
    public String echoFallback(String msg, Throwable throwable) {
        LOG.info("nacos-provider 服务 echo 方法 异常，异常信息如下：" + throwable);
        return "consumer fallback forward : [ " + providerServer.echo(msg) + " ]";
    }
}
