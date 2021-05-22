package cn.hellozuofeng.springcloud.controller;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return paymentService.create(payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
       return paymentService.getPaymentById(id);
    }

}
