package com.earthchen.seckill.service;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.vo.GoodsVo;

public interface SeckillService {


    /**
     * 秒杀方法
     * @param user
     * @param goods
     * @return
     */
    OrderInfo miaosha(SecKillUser user, GoodsVo goods);
}
