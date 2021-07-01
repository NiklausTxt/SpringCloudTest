package com.nik.controller;

import com.nik.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnowflakeController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/snowflake")
    public String getSnoflake(){
        return orderService.getIdBySnowflake();
    }
}
