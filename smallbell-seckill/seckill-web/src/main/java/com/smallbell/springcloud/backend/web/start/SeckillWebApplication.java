package com.smallbell.springcloud.backend.web.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
//        "com.smallbell.springcloud.user",
//        "com.smallbell.springcloud.seckill.remote.fallback",
//        "com.smallbell.springcloud.standard"
}, exclude = {SecurityAutoConfiguration.class})
public class SeckillWebApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SeckillWebApplication.class, args);
    }
}
