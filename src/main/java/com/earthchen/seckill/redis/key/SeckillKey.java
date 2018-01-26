package com.earthchen.seckill.redis.key;

public class SeckillKey extends BasePrefix {

    private SeckillKey(String prefix) {
        super(prefix);
    }

    private SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey("go");
    public static SeckillKey getMiaoshaPath = new SeckillKey(60, "mp");
    public static SeckillKey getMiaoshaVerifyCode = new SeckillKey(300, "vc");
}


