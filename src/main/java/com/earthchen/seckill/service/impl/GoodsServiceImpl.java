package com.earthchen.seckill.service.impl;

import com.earthchen.seckill.dao.GoodsDao;
import com.earthchen.seckill.domain.SecKillGoods;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public void reduceStock(GoodsVo goods) {
        SecKillGoods g = new SecKillGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);

    }
}
