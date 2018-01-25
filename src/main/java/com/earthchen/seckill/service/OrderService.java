package com.earthchen.seckill.service;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillOrder;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.vo.GoodsVo;

public interface OrderService {

    SecKillOrder getMiaoshaOrderByUserIdGoodsId(long id, long goodsId);

    OrderInfo createOrder(SecKillUser user, GoodsVo goods);

    OrderInfo getOrderById(long orderId);

    void deleteOrders();
}
