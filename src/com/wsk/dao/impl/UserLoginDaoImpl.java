package com.wsk.dao.impl;

import com.wsk.commons.jdbcUtils;
import com.wsk.dao.UserLoginDao;
import com.wsk.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginDaoImpl implements UserLoginDao {
    /**
     * 用户登录数据库查询
     * @param username
     * @param userpwd
     * @return
     */


    @Override
    public Users selectUsersByUserNameAndUserpwd(String username, String userpwd) {
        Users user = null;
        Connection conn = null;
        try{
            //获取连接
            conn = jdbcUtils.getConnection();
            //给定需要查询的sql语句
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and userpwd= ?");
            //问号进行参数绑定
            ps.setString(1,username);
            ps.setString(2,userpwd);
            //执行查询
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                user = new Users();
                user.setUsersex(resultSet.getString("usersex"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

        return user;
    }
}
