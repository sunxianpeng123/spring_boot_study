package com.class2.service.user;

import com.class2.model.UserDomain;
import com.github.pagehelper.PageInfo;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/20
 * \* Time: 19:33
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface UserService {
    int addUser(UserDomain user);
    PageInfo<UserDomain> findAllUser(int pageNum,int pageSize);

}