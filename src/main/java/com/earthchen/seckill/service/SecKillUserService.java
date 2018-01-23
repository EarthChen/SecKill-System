package com.earthchen.seckill.service;


import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface SecKillUserService {

    /**
     * 登录
     *
     * @param response
     * @param loginVo
     * @return
     */
    boolean login(HttpServletResponse response, LoginVo loginVo);

    /**
     * 根据id查询user
     *
     * @param id
     * @return
     */
    SecKillUser getById(long id);


    /**
     *
     * @param response
     * @param token
     * @return
     */
    SecKillUser getByToken(HttpServletResponse response, String token);
}
