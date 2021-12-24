package com.smallbell.springcloud.seckill.dao;

import com.smallbell.springcloud.seckill.dao.po.SeckillOrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by SmallBell on 2020/7/18.
 */
@Repository
public interface SeckillOrderDao extends JpaRepository<SeckillOrderPO, Long>, JpaSpecificationExecutor<SeckillOrderPO>
{


}
