package com.earthchen.seckill.vo;

import com.earthchen.seckill.domain.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVo {

    private GoodsVo goods;
    private OrderInfo order;
}
