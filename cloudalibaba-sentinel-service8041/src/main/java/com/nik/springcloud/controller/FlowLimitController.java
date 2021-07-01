package com.nik.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        System.out.println(Thread.currentThread().getName()+"---test");
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "----testB";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotkey")
    public String testHotkey(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
        return "---testHotkey";
    }

    public String deal_testHotkey(String p1, String p2, BlockException exception) {
        return "---deal_testHotkey,";
    }

}