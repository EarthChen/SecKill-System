package com.earthchen.seckill.service;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.vo.GoodsVo;

import java.awt.image.BufferedImage;

public interface SeckillService {


    /**
     * 秒杀方法
     *
     * @param user
     * @param goods
     * @return
     */
    OrderInfo miaosha(SecKillUser user, GoodsVo goods);

    /**
     * 获取秒杀结果
     *
     * @param id
     * @param goodsId
     * @return
     */
    long getMiaoshaResult(Long id, long goodsId);

    /**
     * 检查路径
     *
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    boolean checkPath(SecKillUser user, long goodsId, String path);

    /**
     * 检查验证码
     *
     * @param user
     * @param goodsId
     * @param verifyCode
     * @return
     */
    boolean checkVerifyCode(SecKillUser user, long goodsId, int verifyCode);

    /**
     * 生成秒杀路径
     *
     * @param user
     * @param goodsId
     * @return
     */
    String createMiaoshaPath(SecKillUser user, long goodsId);

    /**
     * 生成图片验证码
     *
     * @param user
     * @param goodsId
     * @return
     */
    BufferedImage createVerifyCode(SecKillUser user, long goodsId);
}
