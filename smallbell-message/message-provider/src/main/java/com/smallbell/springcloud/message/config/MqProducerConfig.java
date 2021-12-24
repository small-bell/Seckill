package com.smallbell.springcloud.message.config;


import com.smallbell.springcloud.message.core.MqSender;
import com.smallbell.springcloud.message.mq.RabbitMqSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "application.message.queue", name = "producer", havingValue = "true" )
@EnableConfigurationProperties(MqConfigProperties.class)
public class MqProducerConfig
{

    private final MqConfigProperties configProperties;


    @Autowired
    public MqProducerConfig(MqConfigProperties configProperties)
    {
        this.configProperties = configProperties;
    }

    @Bean
//    @ConditionalOnExpression("${application.message.queue.producer:true}")
    public MqSender defaultMqProducer()
    {
        return new RabbitMqSender(configProperties);
    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
