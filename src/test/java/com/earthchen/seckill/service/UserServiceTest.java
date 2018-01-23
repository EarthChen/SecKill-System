package com.earthchen.seckill.service;

import com.earthchen.seckill.dao.UserDao;
import com.earthchen.seckill.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getById() {

        User user = userDao.getById(1);
        log.info(user.getName());

    }
}