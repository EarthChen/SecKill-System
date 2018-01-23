package com.earthchen.seckill.controller;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillOrder;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.domain.User;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.result.CodeMsg;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.service.OrderService;
import com.earthchen.seckill.service.SeckillService;
import com.earthchen.seckill.service.UserService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class SeckillController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/do_miaosha")
    public String miaosha(Model model, SecKillUser user,
                          @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        SecKillOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = seckillService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
