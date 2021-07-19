package com.smallbell.common.api.goods;

import com.smallbell.common.api.goods.vo.GoodsVo;

import java.util.List;

/**
 * 商品服务接口
 */
public interface GoodsServiceApi {

    /**
     * 获取商品列表
     *
     * @return
     */
    List<GoodsVo> listGoodsVo();

    /**
     * 通过商品的id查出商品的所有信息（包含该商品的秒杀信息）
     *
     * @param goodsId
     * @return
     */
    GoodsVo getGoodsVoByGoodsId(long goodsId);

    /**
     * 通过商品的id查出商品的所有信息（包含该商品的秒杀信息）
     *
     * @param goodsId
     * @return
     */
    GoodsVo getGoodsVoByGoodsId(Long goodsId);

    /**
     * order表减库存
     *
     * @param goodsId
     */
    void reduceStock(long goodsId);
}