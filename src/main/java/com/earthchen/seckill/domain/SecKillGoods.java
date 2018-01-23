package com.earthchen.seckill.domain;

import lombok.Data;

import java.util.Date;


/**
 * 秒杀商品
 */
@Data
public class SecKillGoods {

    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
