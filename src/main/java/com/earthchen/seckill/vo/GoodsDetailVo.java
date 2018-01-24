package com.earthchen.seckill.vo;

import com.earthchen.seckill.domain.SecKillUser;
import lombok.Data;

@Data
public class GoodsDetailVo {

    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods;
    private SecKillUser user;
}
