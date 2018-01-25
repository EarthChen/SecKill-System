package com.earthchen.seckill.redis.key;

public class OrderKey extends BasePrefix {


    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public OrderKey(String prefix) {
        super(prefix);
    }


    public static OrderKey getById = new OrderKey(60, "id");

    public static OrderKey getSeckillOrderByUidGid = new OrderKey("moug");
}
