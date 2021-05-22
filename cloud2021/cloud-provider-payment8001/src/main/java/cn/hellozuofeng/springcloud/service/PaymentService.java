package cn.hellozuofeng.springcloud.service;

import cn.hellozuofeng.springcloud.entities.Payment;

public interface PaymentService {

    int insertPayment(Payment payment);

    Payment getPaymentById(Long id);

    int deletePaymentById(Long id);

    int updatePaymentById(Payment payment);
}
