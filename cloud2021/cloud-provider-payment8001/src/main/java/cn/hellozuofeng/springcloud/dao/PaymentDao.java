package cn.hellozuofeng.springcloud.dao;

import cn.hellozuofeng.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao {

    int insertPayment(Payment payment);

    int deletePaymentById(@Param("id") Long id);

    Payment getPaymentById(@Param("id") Long id);

    int updatePaymentById(Payment payment);
}
