package cn.hellozuofeng.springcloud.controller;

import cn.hellozuofeng.springcloud.entities.CommonResult;
import cn.hellozuofeng.springcloud.entities.Payment;
import cn.hellozuofeng.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.insertPayment(payment);
        log.info("*** 插入结果：" + result );
        if (result > 0 ) {
            return new CommonResult(200, "server port: " + serverPort + " OK", result);
        } else {
            return new CommonResult(444, "server port: " + serverPort + " ERROR");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Payment payment = paymentService.getPaymentById(id);
        log.info("*** 获取结果：" + payment);
        if (payment != null) {
            return new CommonResult<Payment>(200, "server port: " + serverPort + " OK", payment);
        }
        return new CommonResult(444, "server port: " + serverPort + " ERROR");
    }

    @PostMapping("payment/delete/{id}")
    public CommonResult deletePaymentById(@PathVariable("id") Long id) {
        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        paymentService.deletePaymentById(id);
        return new CommonResult(200, "OK");
    }

    @PostMapping("payment/update")
    public CommonResult updatePaymentById(@RequestBody Payment payment) {
        paymentService.updatePaymentById(payment);
        return new CommonResult(200, "OK");
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("***element: " + element);
        };
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }
}
