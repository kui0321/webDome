package com.wsk.service.impl;

import com.wsk.dao.UserLoginDao;
import com.wsk.dao.impl.UserLoginDaoImpl;
import com.wsk.exception.UserNotFoundException;
import com.wsk.pojo.Users;
import com.wsk.service.UserLoginService;

/**
 * 用户登录业务
 */
public class UserLoginServiceImpl implements UserLoginService {
    /**
     * 用户登录
     * @param username
     * @param userpwd
     * @return
     */

    @Override
    public Users userLogin(String username, String userpwd) {
        UserLoginDao userLoginDao = new UserLoginDaoImpl();
        Users users = userLoginDao.selectUsersByUserNameAndUserpwd(username, userpwd);
        System.out.println(users);
        if (users == null){
             throw new UserNotFoundException("用户名或密码错误");
        }

        return users;
    }
}
