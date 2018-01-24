package com.earthchen.seckill.controller;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillOrder;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.domain.User;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.result.CodeMsg;
import com.earthchen.seckill.result.Result;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.service.OrderService;
import com.earthchen.seckill.service.SeckillService;
import com.earthchen.seckill.service.UserService;
import com.earthchen.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * get为幂等，不对服务端数据产生变化
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @PostMapping(value = "/do_miaosha")
    @ResponseBody
    public Result<OrderInfo> miaosha(Model model, SecKillUser user,
                                     @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);//10个商品，req1 req2
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        SecKillOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = seckillService.miaosha(user, goods);
        return Result.success(orderInfo);
    }
}
