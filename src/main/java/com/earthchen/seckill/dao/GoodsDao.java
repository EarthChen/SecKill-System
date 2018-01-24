package com.earthchen.seckill.dao;

import com.earthchen.seckill.domain.SecKillGoods;
import com.earthchen.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsDao {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price " +
            "from seckill_goods mg left join goods g " +
            "on mg.goods_id = g.id")
    List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price " +
            "from seckill_goods mg left join goods g " +
            "on mg.goods_id = g.id " +
            "where g.id = #{goodsId}")
    GoodsVo getGoodsVoByGoodsId(long goodsId);

    @Update("update seckill_goods " +
            "set stock_count = stock_count - 1 " +
            "where goods_id = #{goodsId}" +
            "and stock_count > 0")
    void reduceStock(SecKillGoods g);
}
