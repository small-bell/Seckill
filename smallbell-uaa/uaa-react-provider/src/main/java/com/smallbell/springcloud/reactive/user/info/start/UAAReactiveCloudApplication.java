package com.smallbell.springcloud.reactive.user.info.start;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration(exclude =  {
        SecurityAutoConfiguration.class,
        ReactiveSecurityAutoConfiguration.class,
        //排除db的自动配置
//        ReactiveUserDetailsServiceAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
        //排除redis的自动配置
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class
})
@SpringBootApplication(scanBasePackages = {
        "com.smallbell.springcloud.reactive",
        "com.smallbell.springcloud.base",
        "com.smallbell.springcloud.seckill.remote.fallback",
        "com.smallbell.springcloud.standard"
})

@EnableTransactionManagement(proxyTargetClass = true)


//@EnableScheduling
@EnableSwagger2
@EnableJpaRepositories(basePackages = {
        "com.smallbell.springcloud.reactive.user.info.dao.impl",
        "com.smallbell.springcloud.reactive.user.*.dao",
        "com.smallbell.springcloud.base.dao"
})

//@EnableRedisRepositories(basePackages = {
//        "com.smallbell.springcloud.user.*.redis"})

@EntityScan(basePackages = {
        "com.smallbell.springcloud.reactive.user.info.entity",
        "com.smallbell.springcloud.reactive.user.*.dao.po",
        "com.smallbell.springcloud.user.*.dao.po",
        "com.smallbell.springcloud.base.dao.po",
        "com.smallbell.springcloud.standard.*.dao.po"})
/**
 *  启用 Eureka Client 客户端组件
*/
@EnableEurekaClient

//启动Feign
//@EnableFeignClients(basePackages =
//        {"com.smallbell.springcloud.seckill.remote.client"},
//        defaultConfiguration = {TokenFeignConfiguration.class}
//)
@Slf4j
//@EnableHystrix
public class UAAReactiveCloudApplication
{


    public static void main(String[] args)
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UAAReactiveCloudApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        String ip = env.getProperty("eureka.instance.ip-address");

      log.info("\n----------------------------------------------------------\n\t" +
                "UAA 用户账号与认证服务 is running! Access URLs:\n\t" +
                "Local: \t\thttp://"+ ip+":"+ port +  path + "/\n\t" +
                "swagger-ui: \thttp://"+ ip+":"+ port +  path + "/doc.html\n\t" +
                "actuator: \thttp://"+ ip+":"+ port +  path + "/actuator/info\n\t" +
                "----------------------------------------------------------");
    }



}