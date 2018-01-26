package com.earthchen.seckill.access;

import com.earthchen.seckill.domain.SecKillUser;

public class UserContext {

    private static ThreadLocal<SecKillUser> userHolder = new ThreadLocal<>();

    public static void setUser(SecKillUser user) {
        userHolder.set(user);
    }

    public static SecKillUser getUser() {
        return userHolder.get();
    }

}