package cn.hellozuofeng.springcloud.controller;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return paymentService.create(payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    //@HystrixCommand
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        //int i = 1 / 0;
        return paymentService.getPaymentById(id);
    }

    /*public CommonResult<Payment> globalFallbackMethod() {
        return new CommonResult<Payment>(444, "服务超时或出现异常（Global）");
    }*/
}
