package com.nik.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IdGeneratorSnowflake {
    private long workid = 0;
    private long datacenterid = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workid, datacenterid);

    @PostConstruct
    public void init(){
        try {
            workid = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            e.printStackTrace();
            workid = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflake(){
        return snowflake.nextId();
    }

    public synchronized long snowflake(long workid,long datacenterid){
        Snowflake snowflake = IdUtil.createSnowflake(workid, datacenterid);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowflake().snowflake() );
    }
}
