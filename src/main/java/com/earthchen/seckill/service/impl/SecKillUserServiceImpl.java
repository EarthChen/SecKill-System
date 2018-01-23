package com.earthchen.seckill.service.impl;

import com.earthchen.seckill.dao.SecKillUserDao;
import com.earthchen.seckill.domain.SecKillUser;
import com.earthchen.seckill.exception.GlobalException;
import com.earthchen.seckill.redis.RedisService;
import com.earthchen.seckill.redis.SecKillUserKey;
import com.earthchen.seckill.result.CodeMsg;
import com.earthchen.seckill.service.SecKillUserService;
import com.earthchen.seckill.util.MD5Util;
import com.earthchen.seckill.util.UUIDUtil;
import com.earthchen.seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@Service
public class SecKillUserServiceImpl implements SecKillUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private RedisService redisService;

    @Autowired
    private SecKillUserDao secKillUserDao;

    @Override
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        SecKillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    /**
     * 使用对象级缓存
     * <p>
     * 对象级缓存一般不设有效期(只要不变化即永久有效)
     *
     * @param id
     * @return
     */
    @Override
    public SecKillUser getById(long id) {
        //取缓存
        SecKillUser user = redisService.get(SecKillUserKey.getById, "" + id, SecKillUser.class);
        if (user != null) {
            return user;
        }
        //取数据库
        user = secKillUserDao.getById(id);
        if (user != null) {
            redisService.set(SecKillUserKey.getById, "" + id, user);
        }
        return user;
    }


    /**
     * 修改用户密码
     * <p>
     * <p>
     * http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
     *
     * @param token
     * @param id
     * @param formPass
     * @return
     */
    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        SecKillUser user = getById(id);
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        SecKillUser toBeUpdate = new SecKillUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        secKillUserDao.update(toBeUpdate);

        //处理缓存
        // 删除redis中原有的id对应的用户信息
        redisService.delete(SecKillUserKey.getById, "" + id);
        user.setPassword(toBeUpdate.getPassword());
        // 更新redis中token对应的用户信息
        redisService.set(SecKillUserKey.token, token, user);
        return true;
    }

    @Override
    public SecKillUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SecKillUser user = redisService.get(SecKillUserKey.token, token, SecKillUser.class);
        //延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }


    private void addCookie(HttpServletResponse response, String token, SecKillUser user) {
        redisService.set(SecKillUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SecKillUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
