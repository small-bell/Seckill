package com.smallbell.springcloud.kafka.mq.consumer;

/**
 * 描述: 对应用户配置的消息监听
 *
 * @author smallbell
 * @date 2021-07-01 11:49
 */
public interface MessageListener {
    /**
     * 消息主题，表示只接受给定主题下的消息
     *
     * @return 消息主题
     */
    String getTopic();

    /**
     * 处理接收到的消息
     *
     * @param message 收到的消息
     */
    void process(ConsumeMessage message);

}
