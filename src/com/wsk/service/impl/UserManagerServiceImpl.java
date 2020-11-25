package com.wsk.service.impl;

import com.wsk.dao.UserMangerDao;
import com.wsk.dao.impl.UserMangerDaoImpl;
import com.wsk.pojo.Users;
import com.wsk.service.UserManagerService;

import java.util.List;

public class UserManagerServiceImpl implements UserManagerService {
    /**
     * 添加用户
     * @param users
     */
    @Override
    public void addUser(Users users) {
        UserMangerDao userMangerDao = new UserMangerDaoImpl();
        userMangerDao.insertUser(users);
    }

    /**
     * 查询用户
     * @param users
     * @return
     */
    @Override
    public List<Users> findUser(Users users) {
        UserMangerDao userMangerDao = new UserMangerDaoImpl();
        return userMangerDao.selectUserByProperty(users);
    }


    /**
     * 预更新用户查询
     * @return
     */
    @Override
    public Users findUserByUserid(int userid) {
        UserMangerDao userMangerDao = new UserMangerDaoImpl();
        return userMangerDao.selectUserByUserId(userid);
    }

    /**
     * 修改用户
     * @param users
     */
    @Override
    public void modifyUser(Users users) {
        UserMangerDao userMangerDao = new UserMangerDaoImpl();
        userMangerDao.updateUserByUserId(users);
    }

    /**
     * 实现删除用户业务层方法
     * @param userid
     */
    @Override
    public void dropUser(int userid) {
        UserMangerDao userMangerDao = new UserMangerDaoImpl();
        userMangerDao.deletUserByUserId(userid);
    }


}
