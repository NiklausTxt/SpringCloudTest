package com.nik.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nik.springcloud.entities.CommonResult;
import com.nik.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {public static final String SERVICE_URL = "http://cloudalibaba-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback",fallback = "handleFallback")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handleFallback",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    public CommonResult handleFallback(@PathVariable Long id, Throwable throwable) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "fallback,exception 内容" + throwable.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException throwable) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(555, "BlockException,exception 内容" + throwable.getMessage(), payment);

    }

}
