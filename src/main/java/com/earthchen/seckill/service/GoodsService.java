package com.earthchen.seckill.service;

import com.earthchen.seckill.vo.GoodsVo;

import java.util.List;

public interface GoodsService {


    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> listGoodsVo();


    /**
     * 根据商品id获取商品信息
     * @param goodsId
     * @return
     */
    GoodsVo getGoodsVoByGoodsId(long goodsId) ;


    /**
     * 减少库存
     * @param goods
     */
    void reduceStock(GoodsVo goods);



}
