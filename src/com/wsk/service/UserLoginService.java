package com.wsk.service;

import com.wsk.pojo.Users;

public interface UserLoginService {
    Users userLogin(String username, String userpwd);
}
