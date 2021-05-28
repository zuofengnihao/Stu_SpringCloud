package cn.hellozuofeng.nacosdemo.controller;

import cn.hellozuofeng.nacosdemo.service.TestService;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZuoFeng
 * @Date: 2021/5/28 09:43
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/echo/{msg}")
    public String echo(@PathVariable("msg") String msg) {
        return testService.echo(msg);
    }
}
