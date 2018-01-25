package com.earthchen.seckill.service.impl;

import com.earthchen.seckill.dao.OrderDao;
import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillOrder;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.redis.key.OrderKey;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.service.OrderService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceIml implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RedisService redisService;


    /**
     * 根据用户id和商品id查询秒杀订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public SecKillOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
//        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        return redisService.get(OrderKey.getSeckillOrderByUidGid, "" + userId + "_" + goodsId, SecKillOrder.class);
    }

    /**
     * 创建订单
     *
     * @param user
     * @param goods
     * @return
     */
    @Transactional
    @Override
    public OrderInfo createOrder(SecKillUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderDao.insert(orderInfo);
        SecKillOrder miaoshaOrder = new SecKillOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderInfo.getId());
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertSeckillOrder(miaoshaOrder);

        redisService.set(OrderKey.getSeckillOrderByUidGid, "" + user.getId() + "_" + goods.getId(), miaoshaOrder);

        return orderInfo;
    }

    @Override
    public OrderInfo getOrderById(long orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public void deleteOrders() {
        orderDao.deleteOrders();
        orderDao.deleteMiaoshaOrders();
    }
}
