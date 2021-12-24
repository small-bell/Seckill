package com.smallbell.springcloud.seckill.remote.fallback;


import com.smallbell.springcloud.common.page.PageOut;
import com.smallbell.springcloud.common.page.PageReq;
import com.smallbell.springcloud.common.result.RestOut;
import com.smallbell.springcloud.seckill.api.dto.SeckillOrderDTO;
import com.smallbell.springcloud.seckill.remote.client.SeckillOrderClient;
import org.springframework.stereotype.Component;

@Component
public class SeckillClientDefaultFallback implements SeckillOrderClient
{


    /**
     * 查询用户订单信息
     *
     * @param userId 用户id
     * @return 商品 dto
     */
    public RestOut<PageOut<SeckillOrderDTO>> userOrders(Long userId, PageReq pageReq)
    {

        return RestOut.error("远程调用失败,返回熔断后的调用结果" );
    }


}
