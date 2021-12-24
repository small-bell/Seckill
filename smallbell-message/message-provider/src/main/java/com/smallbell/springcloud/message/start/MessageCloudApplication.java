package com.smallbell.springcloud.message.start;


import com.smallbell.springcloud.standard.config.TokenFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages =
        {"com.smallbell.springcloud.message",
                "com.smallbell.springcloud.standard",
                "com.smallbell.springcloud.user.info.contract.fallbak"
        })
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.smallbell.springcloud.message.dao"})
@EntityScan(basePackages = {"com.smallbell.springcloud.message.dao.po", "com.smallbell.springcloud.standard.*.dao.po"})

@EnableFeignClients(basePackages = "com.smallbell.springcloud.user.info.api.client",
        defaultConfiguration = {TokenFeignConfiguration.class})
@EnableSwagger2
@EnableHystrix
@EnableCircuitBreaker
@EnableEurekaClient
public class MessageCloudApplication extends SpringBootServletInitializer
{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        setRegisterErrorPageFilter(false);
        return application.sources(MessageCloudApplication.class);
    }


    public static void main(String[] args)
    {
        SpringApplication.run(MessageCloudApplication.class, args);
    }

}
