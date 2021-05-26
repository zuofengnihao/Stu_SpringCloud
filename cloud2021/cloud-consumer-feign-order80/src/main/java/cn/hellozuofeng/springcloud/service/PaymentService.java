package cn.hellozuofeng.springcloud.service;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE", fallback = PaymentServiceImpl.class)
@Component
public interface PaymentService {

    @PostMapping("/payment/create")
    CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    @PostMapping("payment/delete/{id}")
    CommonResult deletePaymentById(@PathVariable("id") Long id);

    @PostMapping("payment/update")
    CommonResult updatePaymentById(@RequestBody Payment payment);

    @GetMapping("/payment/discovery")
    Object discovery();
}
