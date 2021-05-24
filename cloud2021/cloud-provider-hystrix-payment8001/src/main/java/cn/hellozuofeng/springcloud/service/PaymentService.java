package cn.hellozuofeng.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String payment_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "\tpayment_OK,id：" + id;
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String payment_TimeOut(Integer id) {
        int time = 5;
        int age = time/0;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "\tpayment_timeout,id：" + id + "\t耗时（秒）" + time;
    }

    public String fallbackMethod(Integer id) {
        return "请求超时：！！！ 线程池：" + Thread.currentThread().getName() + "\tpayment_timeout,id：" + id;
    }
}
