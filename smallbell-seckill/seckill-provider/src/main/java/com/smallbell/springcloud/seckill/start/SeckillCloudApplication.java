package com.smallbell.springcloud.seckill.start;


import com.smallbell.springcloud.standard.config.FeignConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication(scanBasePackages =
        {"com.smallbell.springcloud.seckill",
                "com.smallbell.springcloud.standard",
                "com.smallbell.springcloud.base",
                "com.smallbell.springcloud.message",
                "com.smallbell.springcloud.user.info.remote.fallback"
        }, exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
@EnableSwagger2
@EnableJpaRepositories(basePackages = {
        "com.smallbell.springcloud.seckill.dao",
        "com.smallbell.springcloud.base.dao"
})
@EntityScan(basePackages = {
        "com.smallbell.springcloud.seckill.dao.po",
        "com.smallbell.springcloud.base.dao.po",
        "com.smallbell.springcloud.standard.*.dao.po"
})
@EnableHystrix
@EnableFeignClients(basePackages = "com.smallbell.springcloud.user.info.api.client", defaultConfiguration = FeignConfiguration.class)
@EnableTransactionManagement
public class SeckillCloudApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SeckillCloudApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");

        log.info("\n----------------------------------------------------------\n\t" +
                "秒杀练习服务 is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "swagger-ui: \thttp://localhost:" + port + path + "/swagger-ui.html\n\t" +
                "actuator: \thttp://localhost:" + port + path + "/actuator/info\n\t" +
                "----------------------------------------------------------");
    }

}