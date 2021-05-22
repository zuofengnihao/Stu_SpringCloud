package cn.hellozuofeng.springcloud.service;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
@Component
public interface PaymentService {

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);

    @PostMapping("payment/delete/{id}")
    public CommonResult deletePaymentById(@PathVariable("id") Long id);

    @PostMapping("payment/update")
    public CommonResult updatePaymentById(@RequestBody Payment payment);
}
