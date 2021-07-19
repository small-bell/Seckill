package com.smallbell.common.api.mq;

import com.smallbell.common.api.mq.vo.SkMessage;

/**
 * 消息队列服务
 */
public interface MqProviderApi {

    /**
     * 将用户秒杀信息投递到MQ中（使用direct模式的exchange）
     *
     * @param message
     */
    void sendSkMessage(SkMessage message);
}
