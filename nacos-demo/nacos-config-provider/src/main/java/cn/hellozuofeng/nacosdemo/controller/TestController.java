package cn.hellozuofeng.nacosdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/6/2 10:10
 * @Description:
 */
@Api(tags = "生产者测试接口")
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${myName}")
    private String myName;

    @ApiOperation(value = "生产者回音测试")
    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable("msg") String msg) {
        //int a = 1 / 0;
        return " nacos-provider echo : " + msg + " and myName is " + myName;
    }
}
