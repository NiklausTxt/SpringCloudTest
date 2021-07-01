package com.nik.springcloud.controller;

import javax.annotation.Resource;

import com.nik.springcloud.entities.CommonResult;
import com.nik.springcloud.entities.Payment;
import com.nik.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        if (res > 0) {
            System.out.println("dev002");
            return new CommonResult(200,"插入成功",res);
        }else{
            return new CommonResult(444,"插入失败",null);
        }

    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        System.out.println("dfsdsgfg");
        if (payment != null) {
            System.out.println("changed");
            System.out.println("dev001");
            return new CommonResult(200,"查询成功",payment);
        } else {
            return new CommonResult(444,"没有该记录",null);
        }
    }

}
