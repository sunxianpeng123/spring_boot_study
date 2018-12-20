package com.class2.dao;


import com.class2.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//注意：一定不要忘了使用@Mapper注解，如果没有这个注解，spring就无法扫描到这个类，导致项目启动报错。
public interface UserDao {
    int insert(UserDomain record);
//    void deleteUserById(@Param("userId") Integer userId);

//    void updateUser(UserDomain userDomain);
    List<UserDomain> selectUsers();


}
