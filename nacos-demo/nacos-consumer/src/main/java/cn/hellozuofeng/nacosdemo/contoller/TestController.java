package cn.hellozuofeng.nacosdemo.contoller;

import cn.hellozuofeng.nacosdemo.client.ProviderClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/27 13:47
 * @Description:
 */
@Api(tags = "消费者测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProviderClient providerClient;

    @ApiOperation(value = "消费者回音测试")
    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable("msg") String msg) {
        return "consumer forward : [ " + providerClient.echo(msg) + " ]";
    }
}
