package com.nik.service;

import com.nik.util.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderService {

    @Autowired
    private IdGeneratorSnowflake idGenerator;

    public String getIdBySnowflake(){
        String ids="";
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName());
            threadPool.submit(()->{
                System.out.println(idGenerator.snowflake());
            });

        }

        threadPool.shutdown();
        return idGenerator.snowflake()+"";
    }
}
