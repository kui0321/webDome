package com.wsk.service;

import com.wsk.pojo.Users;

import java.util.List;

public interface UserManagerService {
    void addUser(Users users);
    List<Users> findUser(Users users);
    Users findUserByUserid(int userid);
    //创建更新用户业务的接口
    void modifyUser(Users users);
    //创建删除用户业务层接口
    void dropUser(int userid);
}
