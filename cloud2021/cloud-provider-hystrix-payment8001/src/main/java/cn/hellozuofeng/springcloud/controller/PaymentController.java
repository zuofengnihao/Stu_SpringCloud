package cn.hellozuofeng.springcloud.controller;

import cn.hellozuofeng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment/ok/{id}")
    public String payment_OK(@PathVariable("id") Integer id) {
        String result = paymentService.payment_OK(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping("/payment/timeout/{id}")
    public String payment_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.payment_TimeOut(id);
        log.info("result:" + result);
        return result;
    }
}
