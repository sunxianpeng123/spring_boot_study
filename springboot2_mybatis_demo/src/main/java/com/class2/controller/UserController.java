package com.class2.controller;

import com.class2.dao.UserDao;
import com.class2.model.UserDomain;
import com.class2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/20
 * \* Time: 19:44
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
//        http://localhost:8080/user/add?userName=测试mybatisAF&password=123456&phone=18516356051

        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name ="pageNum",required = false,defaultValue = "1")
            int pageNum,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10")
            int pageSize
    ){
//        http://localhost:8080/user/all?pageNum=5&pageSize=12
        return  userService.findAllUser(pageNum,pageSize);
    }

}