package cn.hellozuofeng.springcloud.controller;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private int serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*** 插入结果：" + result );
        if (result > 0 ) {
            return new CommonResult(200, "server port: " + serverPort + " OK", result);
        } else {
            return new CommonResult(444, "server port: " + serverPort + " ERROR");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*** 获取结果：" + payment);
        if (payment != null) {
            return new CommonResult<Payment>(200, "server port: " + serverPort + " OK", payment);
        }
        return new CommonResult(444, "server port: " + serverPort + " ERROR");
    }
}
