package com.earthchen.seckill.domain;

import lombok.Data;

/**
 * 秒杀订单
 */
@Data
public class SecKillOrder {

    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
