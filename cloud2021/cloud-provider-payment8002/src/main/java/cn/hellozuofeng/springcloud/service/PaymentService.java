package cn.hellozuofeng.springcloud.service;

import cn.hellozuofeng.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
