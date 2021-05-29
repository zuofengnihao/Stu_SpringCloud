package cn.hellozuofeng.springcloud.service.impl;

import cn.hellozuofeng.springcloud.dao.PaymentDao;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;


    public int insertPayment(Payment payment) {
        return paymentDao.insertPayment(payment);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Payment getPaymentById(Long id) {
        // 抛出异常
        //int i = 1/0;
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return paymentDao.getPaymentById(id);
    }

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

    public int updatePaymentById(Payment payment) {
        return paymentDao.updatePaymentById(payment);
    }

    public Payment fallbackMethod(Long id) {
        log.info("getPaymentById 服务发生降级！");
        return null;
    }

    public int deletePaymentByIdHandler(Long id) {
        return -1;
    }
}
