package com.earthchen.seckill.controller;


import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.service.SecKillUserService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping("/to_list")
    public String list(Model model,
                       SecKillUser user) {
        List<GoodsVo> goodsVoList = goodsService.listGoodsVo();

        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";
    }

}