package com.class1.controller;

import com.class1.bean.UserDomain;
import com.class1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2018/12/19
 * \* Time: 19:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/user")
//    http://localhost:8080/user?userId=1&userName=%E6%B5%8B%E8%AF%95&password=123456&phone=18516356051
public class UserController {
    /**
     * 强行科普一下：
     * @RequestParam 用于将请求参数区数据映射到功能处理方法的参数上，value：参数名字，即入参的请求参数名字，
     * 如userName表示请求的参数区中的名字为userName的参数的值将传入，required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
     * @Controller和@RestController的区别？
     */
    @Autowired
    private UserService userService;


    @PostMapping("")
    public ResponseEntity addUser(
            @RequestParam(value = "userName", required = true) String userName, @RequestParam(value = "password", required = true)
            String password,
            @RequestParam(value = "phone", required = false)
                    String phone
    ){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName(userName);
        userDomain.setPassword(password);
        userDomain.setPhone(phone);
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功"); }

    @DeleteMapping("")
    public ResponseEntity deleteUser(
            @RequestParam(value = "userId", required = true) Integer userId){
                userService.deleteUserById(userId);
                return ResponseEntity.ok("删除成功");
            }

    @PutMapping("")
    public ResponseEntity updateUser(
            @RequestParam(value = "userId", required = true)
                    Integer userId,
            @RequestParam(value = "userName", required = false)
                    String userName,
            @RequestParam(value = "password", required = false)
                    String password,
            @RequestParam(value = "phone", required = false)
                    String phone ){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserId(userId); userDomain.setUserName(userName);
        userDomain.setPassword(password); userDomain.setPhone(phone);
        userService.updateUser(userDomain);
        return ResponseEntity.ok("更新成功");
            }

    @GetMapping("")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.selectUsers());
    }




}
