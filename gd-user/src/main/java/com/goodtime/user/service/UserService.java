package com.goodtime.user.service;

import com.goodtime.user.dao.UserMapper;
import com.goodtime.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户Service类
 * Created by zhongcy on 2016/5/12.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User userTest() {
        User user = new User();
        //throw new RuntimeException();
        return userMapper.selectByPrimaryKey(1);
    }

}
