package com.earthchen.seckill.redis.key;

public class GoodsKey extends BasePrefix {

    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "goodList");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "goodDetail");
    public static GoodsKey getMiaoshaGoodsStock = new GoodsKey(0, "goodStock");
}
