package com.wsk.dao.impl;

import com.wsk.commons.jdbcUtils;
import com.wsk.dao.UserMangerDao;
import com.wsk.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserMangerDaoImpl implements UserMangerDao {

    @Override
    public void insertUser(Users users) {
        Connection conn = null;
        try{
            conn = jdbcUtils.getConnection();
            //手动提交事务
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into users values (default ,?, ?, ?, ?, ?)");
            ps.setString(1,users.getUsername());
            ps.setString(2,users.getUserpwd());
            ps.setString(3, users.getUsersex());
            ps.setString(4, users.getPhonenumber());
            ps.setString(5, users.getQqnumber());
            ps.execute();
            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
            jdbcUtils.rollbackConnection(conn);
        }finally {
            jdbcUtils.closeConnection(conn);
        }


    }

    /**
     * 查询用户
     * @param users
     * @return
     */
    @Override
    public List<Users> selectUserByProperty(Users users) {
        Connection conn = null;
        List<Users> list = new ArrayList<>();

        try{
            conn = jdbcUtils.getConnection();
            String sql = this.createSql(users);
            System.out.println(sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Users user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                user.setUsersex(resultSet.getString("usersex"));
                list.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcUtils.closeConnection(conn);
        }
        return list;
    }

    /**
     * 根据用户Id查询用户
     * @param userid
     * @return
     */
    @Override
    public Users selectUserByUserId(int userid) {
        Connection conn = null;
        Users user = null;
        try{
            conn = jdbcUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from users where userid = ?");
            preparedStatement.setInt(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUsername(resultSet.getString("username"));
                user.setUsersex(resultSet.getString("usersex"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jdbcUtils.closeConnection(conn);
        }
        return user;
    }

    /**
     * 更新用户
     * @param users
     */
    @Override
    public void updateUserByUserId(Users users) {
        Connection conn = null;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("update users set username=?, usersex=?, phonenumber=?, qqnumber=? where userid=?");
            preparedStatement.setString(1,users.getUsername());
            preparedStatement.setString(2,users.getUsersex());
            preparedStatement.setString(3,users.getPhonenumber());
            preparedStatement.setString(4,users.getQqnumber());
            preparedStatement.setInt(5,users.getUserid());
            preparedStatement.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            jdbcUtils.rollbackConnection(conn);
        }finally {
            jdbcUtils.closeConnection(conn);

        }
    }

    @Override
    public void deletUserByUserId(int userid) {
        Connection conn = null;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement("delete from users where userid = ?");
            preparedStatement.setInt(1,userid);
            preparedStatement.execute();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            jdbcUtils.rollbackConnection(conn);
        }finally {
            jdbcUtils.closeConnection(conn);
        }
    }

    /**
     *创建拼接的SQL语句
     * @param users
     * @return
     */
    private String createSql(Users users){
        StringBuffer stringBuffer = new StringBuffer("select * from users where 1=1 ");

        if (users.getUsersex() != null && users.getUsersex().length() >0){
            stringBuffer.append(" and usersex = " + users.getUsersex() );
        }
        if (users.getQqnumber() != null && users.getQqnumber().length()>0){
            stringBuffer.append(" and qqnumber = " + users.getQqnumber());
        }
        if(users.getUsername() != null && users.getUsername().length() > 0){
            stringBuffer.append(" and username = " + users.getUsername());
        }
        if (users.getPhonenumber() != null && users.getPhonenumber().length() > 0){
            stringBuffer.append(" and phonenumber = " + users.getPhonenumber());
        }

        return stringBuffer.toString();
    }
}
