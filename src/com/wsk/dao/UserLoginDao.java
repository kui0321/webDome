package com.wsk.dao;

import com.wsk.pojo.Users;

public interface UserLoginDao {
    public Users selectUsersByUserNameAndUserpwd(String username, String userpwd);
}
