package com.earthchen.seckill.rabbitmq;

import com.earthchen.seckill.domain.SecKillUser;
import lombok.Data;

/**
 * 秒杀消息
 */
@Data
public class SeckillMessage {

    private SecKillUser user;
    private long goodsId;
}
