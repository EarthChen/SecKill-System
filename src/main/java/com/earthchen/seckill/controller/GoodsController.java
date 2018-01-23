package com.earthchen.seckill.controller;


import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.service.SecKillUserService;
import com.earthchen.seckill.service.impl.SecKillUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private SecKillUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String list(Model model, HttpServletResponse response,
SecKillUser user
//                       @CookieValue(value = SecKillUserServiceImpl.COOKIE_NAME_TOKEN, required = false) String cookieToken,
//                       @RequestParam(value = SecKillUserServiceImpl.COOKIE_NAME_TOKEN, required = false) String paramToken
) {
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//            return "login";
//        }
//
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
//        SecKillUser user = userService.getByToken(response, token);

        model.addAttribute("user", user);
        return "goods_list";
    }

}