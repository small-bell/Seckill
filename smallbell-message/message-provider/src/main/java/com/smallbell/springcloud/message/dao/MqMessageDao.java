package com.smallbell.springcloud.message.dao;

import com.smallbell.springcloud.message.dao.po.MqMessagePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by SmallBell on 2020/7/18.
 */
@Repository
public interface MqMessageDao extends JpaRepository<MqMessagePO, String>, JpaSpecificationExecutor<MqMessagePO>
{


}
