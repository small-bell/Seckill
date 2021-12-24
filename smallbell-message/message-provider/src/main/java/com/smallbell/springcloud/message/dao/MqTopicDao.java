package com.smallbell.springcloud.message.dao;

import com.smallbell.springcloud.message.dao.po.MqMessagePO;
import com.smallbell.springcloud.message.dao.po.MqTopicPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SmallBell on 2020/7/18.
 */
@Repository
public interface MqTopicDao extends JpaRepository<MqTopicPO, String>, JpaSpecificationExecutor<MqTopicPO>
{


    /**
     * 根据 topicCode 做查询
     *
     * @param topicCode 编码
     * @return MqMessagePO 列表
     */

    List<MqMessagePO> findAllByTopicCode(String topicCode);
}
