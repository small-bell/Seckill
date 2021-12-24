package com.smallbell.springcloud.kafka.autoconfigure;

import com.smallbell.springcloud.kafka.mq.admin.KafkaAdmin;
import com.smallbell.springcloud.kafka.mq.consumer.internal.kafka.KafkaConsumerServer;
import com.smallbell.springcloud.kafka.mq.producer.internal.kafka.KafkaProducerServer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: Spring Boot Starter初始化
 * 1. 将EnableConfigurationProperties 加载到Spring上下文的容器中
 * 2. 当配置文件存在“com.smallbell.springcloud.mq.kafka.bootstrap-server”时新建对象
 *
 * @author smallbell
 * @date 2021-07-02 12:49
 */
@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
@ConditionalOnClass({KafkaProducerServer.class, KafkaConsumer.class})
@ConditionalOnProperty(prefix = "mq.kafka", name = "bootstrap-server")
public class KafkaAutoConfiguration
{

    @Bean
    KafkaProperties kafkaProperties()
    {
        return new KafkaProperties();
    }

    @Bean(initMethod = "init")
    public KafkaProducerServer kafkaProducerServer()
    {
        return new KafkaProducerServer();
    }

    @Bean(initMethod = "init")
    public KafkaConsumerServer kafkaConsumerServer()
    {
        return new KafkaConsumerServer(applicationName);
    }

    @Bean
    public KafkaAdmin kafkaAdmin(KafkaProperties kafkaProperties ) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootstrapServer());
        return new KafkaAdmin(configs);
    }

    @Value("${spring.application.name}")
    private String applicationName;
}
