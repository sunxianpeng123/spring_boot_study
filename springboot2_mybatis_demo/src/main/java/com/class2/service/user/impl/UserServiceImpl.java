package com.class2.service.user.impl;

import com.class2.dao.UserDao;
import com.class2.model.UserDomain;
import com.class2.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/20
 * \* Time: 19:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int addUser(UserDomain user){
        return userDao.insert(user);
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum,int pageSize){
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum,pageSize);
        List<UserDomain> userDomains=userDao.selectUsers();
        PageInfo result =new PageInfo(userDomains);
        return result;
    }


}