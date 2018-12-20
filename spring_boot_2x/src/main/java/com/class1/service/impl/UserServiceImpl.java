package com.class1.service.impl;

import com.class1.bean.UserDomain;
import com.class1.dao.UserDao;
import com.class1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/19
 * \* Time: 19:27
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;//这里会爆红，请忽略
    // @Override
    public int insert(UserDomain record) {
        return userDao.insert(record);
    } @Override
    public void deleteUserById(Integer userId) {
        userDao.deleteUserById(userId);
    } @Override
    public void updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain); }
    @Override
    public List<UserDomain> selectUsers() {
        return userDao.selectUsers();
    }


}