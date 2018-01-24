package com.earthchen.seckill.controller;

import com.earthchen.seckill.domain.OrderInfo;
import com.earthchen.seckill.domain.SecKillOrder;
import com.earthchen.seckill.result.CodeMsg;
import com.earthchen.seckill.result.Result;
import com.earthchen.seckill.service.GoodsService;
import com.earthchen.seckill.service.OrderService;
import com.earthchen.seckill.vo.GoodsVo;
import com.earthchen.seckill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;


    /**
     * 获取订单详情
     * <p>
     * 根据订单id
     *
     * @param user
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public Result<OrderDetailVo> info(SecKillOrder user,
                                      @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return Result.success(vo);
    }
}
