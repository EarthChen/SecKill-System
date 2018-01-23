package com.earthchen.seckill.service.impl;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.domain.User;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.service.OrderService;
import com.earthchen.seckill.service.SeckillService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public OrderInfo miaosha(SecKillUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info maiosha_order
        return orderService.createOrder(user, goods);
    }
}
