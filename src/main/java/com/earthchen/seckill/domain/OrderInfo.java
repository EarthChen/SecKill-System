package com.earthchen.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * 订单信息
 */
@Data
public class OrderInfo {

    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;

    /**
     * 订单渠道
     * 0 pc
     * 1 android
     * 2 ios
     */
    private Integer orderChannel;

    /**
     * 订单状态
     * 0 新建未支付
     * 1 已支付
     * 2 已发货
     * 3 已收货
     * 4 已退款
     * 5 已完成
     */
    private Integer status;
    private Date createDate;
    private Date payDate;
}
