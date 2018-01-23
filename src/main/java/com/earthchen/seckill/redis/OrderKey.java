package com.earthchen.seckill.redis;

public class OrderKey extends BasePrefix {


    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static OrderKey getById = new OrderKey(60, "id");
}
