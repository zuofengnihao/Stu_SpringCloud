package cn.hellozuofeng.springcloud.service.impl;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {


    public CommonResult create(Payment payment) {
        return new CommonResult(444, "create fallback");
    }

    public CommonResult getPaymentById(Long id) {
        return new CommonResult(444, "getPaymentById fallback");
    }

    public CommonResult deletePaymentById(Long id) {
        return new CommonResult(444, "deletePaymentById fallback");
    }

    public CommonResult updatePaymentById(Payment payment) {
        return new CommonResult(444, "updatePaymentById fallback");
    }

    public Object discovery() {
        return new CommonResult(444, "discovery fallback");
    }
}
