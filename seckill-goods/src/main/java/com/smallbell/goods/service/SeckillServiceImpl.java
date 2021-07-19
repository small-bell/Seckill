package com.smallbell.goods.service;


import com.alibaba.fastjson.JSONObject;
import com.smallbell.common.api.cache.RedisServiceApi;
import com.smallbell.common.api.goods.GoodsServiceApi;
import com.smallbell.common.api.order.OrderServiceApi;
import com.smallbell.common.api.seckill.SeckillServiceApi;
import com.smallbell.common.api.seckill.vo.VerifyCodeVo;
import com.smallbell.common.api.user.vo.UserVo;
import com.smallbell.common.domain.OrderInfo;
import com.smallbell.common.domain.SeckillOrder;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 秒杀服务接口实现
 *
 * @author mata
 */
@Service(interfaceClass = SeckillServiceApi.class)
public class SeckillServiceImpl implements SeckillServiceApi {

    // private static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    GoodsServiceApi goodsService;

    @Reference(interfaceClass = OrderServiceApi.class)
    OrderServiceApi orderService;

    @Reference(interfaceClass = RedisServiceApi.class)
    RedisServiceApi redisService;

    /**
     * 用于生成验证码中的运算符
     */
    private char[] ops = new char[]{'+', '-', '*'};

    /**
     * 减库存，生成订单，实现秒杀操作核心业务
     * 秒杀操作由两步构成，不可分割，为一个事务
     *
     * @param userID  秒杀商品的用户唯一ID号
     * @param goods 所秒杀的商品
     * @return
     */
    @Transactional
    @Override
    public OrderInfo seckill(long userId, long goodsId) {

        // 1. 减库存
        goodsService.reduceStock(goodsId);
        // 2. 生成订单；向 order_info 表和 seckill_order 表中写入订单信息
        OrderInfo order = orderService.createOrder(userId,goodsId);

        // logger.info("订单生成成功");

        return order;
    }


    /**
     * 获取秒杀结果
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public long getSeckillResult(Long userId, long goodsId) {

        SeckillOrder order = orderService.getSeckillOrderByUserIdAndGoodsId(userId, goodsId);
        if (order != null) {//秒杀成功
            return order.getOrderId();
        } 
        return -1;       
    }

    /**
     * 创建验证码
     *
     * @param user
     * @param goodsId
     * @return
     */
    @Override
    public String createVerifyCode(UserVo user, long goodsId) {
        if (user == null || goodsId <= 0) {
            return null;
        }

        // 验证码的宽高
        int width = 80;
        int height = 32;

        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode, 8, 24);
        g.dispose();

        // 计算表达式值，并把把验证码值存到redis中
        int expResult = calc(verifyCode);
        //输出图片和结果
        VerifyCodeVo verifyCodeVo = new VerifyCodeVo(image, expResult);
        String verifyCodeVoJson = JSONObject.toJSONString(verifyCodeVo);
        return verifyCodeVoJson;
    }

    /**
     * 使用ScriptEngine计算验证码中的数学表达式的值
     *
     * @param exp
     * @return
     */
    private int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);// 表达式计算
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 生成验证码，只含有+/-/*
     * <p>
     * 随机生成三个数字，然后生成表达式
     *
     * @param rdm
     * @return 验证码中的数学表达式
     */
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2 + op2 + num3;
        return exp;
    }
}
