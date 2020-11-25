package com.wsk.dao;

import com.wsk.pojo.Users;

import java.util.List;

public interface UserMangerDao {
    void insertUser(Users users);
    //创建查询业务持久层接口
    List<Users> selectUserByProperty(Users users);
    //更新业务查询持久层接口
    Users selectUserByUserId(int userid);
    //创建更新用户持久层
    void updateUserByUserId(Users users);
    //创建删除业务持久层
    void deletUserByUserId(int userid);
}
