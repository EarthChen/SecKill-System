package com.earthchen.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.earthchen.seckill.dao")
public class SeckillApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }

    //    打war包需要
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SeckillApplication.class);
    }
}
