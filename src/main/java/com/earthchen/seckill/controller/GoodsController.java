package com.earthchen.seckill.controller;


import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.domain.User;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.service.SecKillUserService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private SecKillUserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/to_list")
    public String list(Model model,
                       SecKillUser user) {
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();

        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";
    }

    @GetMapping("/to_detail/{goodsId}")
    public String detail(Model model, User user,
                         @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}