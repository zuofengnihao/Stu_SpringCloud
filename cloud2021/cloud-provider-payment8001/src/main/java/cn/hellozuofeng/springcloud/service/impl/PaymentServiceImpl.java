package cn.hellozuofeng.springcloud.service.impl;

import cn.hellozuofeng.springcloud.dao.PaymentDao;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;


    public int insertPayment(Payment payment) {
        return paymentDao.insertPayment(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    public int deletePaymentById(Long id) {
        return paymentDao.deletePaymentById(id);
    }

    public int updatePaymentById(Payment payment) {
        return paymentDao.updatePaymentById(payment);
    }


}
