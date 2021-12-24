package com.smallbell.springcloud.sharding.jdbc.demo.start;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableConfigurationProperties

@SpringBootApplication(scanBasePackages =
        {"com.smallbell.springcloud.sharding.jdbc.demo",
//                 "com.smallbell.springcloud.base",
//                 "com.smallbell.springcloud.standard"
        }, exclude = {
        DataSourceAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        DruidDataSourceAutoConfigure.class})
@EnableScheduling
@EnableSwagger2
@EnableJpaRepositories(basePackages = {
        "com.smallbell.springcloud.sharding.jdbc.demo.dao.impl",
//        "com.smallbell.springcloud.base.dao"
})
@EnableTransactionManagement(proxyTargetClass = true)

@EntityScan(basePackages = {
//        "com.smallbell.springcloud.user.*.dao.po",
        "com.smallbell.springcloud.sharding.jdbc.demo.entity.jpa",
//        "com.smallbell.springcloud.standard.*.dao.po"
})
/**
 * 启用 Hystrix
 */
//@EnableHystrix
//@EnableFeignClients(
//        basePackages = "com.smallbell.springcloud.user.info.remote.client",
//        defaultConfiguration = FeignConfiguration.class)
//@Slf4j
//@EnableEurekaClient
public class ShardingJdbcDemoCloudApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ShardingJdbcDemoCloudApplication.class, args);


        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/index.html\n\t" +
                "swagger-ui: \thttp://localhost:" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------------");

    }
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
//    {
//
//        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
//
//        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
//
//        return placeholderConfigurer;
//    }
}
