package com.class1.service;

import com.class1.bean.UserDomain;

import java.util.List;

public interface UserService {
    int insert(UserDomain record);
    void deleteUserById(Integer userId);
    void updateUser(UserDomain userDomain);
    List<UserDomain> selectUsers();

}
