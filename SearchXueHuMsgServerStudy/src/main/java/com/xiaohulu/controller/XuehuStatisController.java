package com.xiaohulu.controller;

import com.xiaohulu.log.Log;
import com.xiaohulu.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/4/15
 * \* Time: 21:11
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController

@Api(description="雪狐数据统计,自实验")
public class XuehuStatisController {

    @ApiOperation(value="获取平台分类统计数据", notes="根据传递平台和月份获取统计数")
   	@RequestMapping(value = "/getStatisticsByPlatAndMonth", method = {RequestMethod.GET})
    public boolean getStatisticsByPlatAndMonth(
            @ApiParam(value = "平台号", required = true)
            @RequestParam(value = "platID", required = true) int platID,
            @ApiParam(value = "month,格式 2018-01 形式", required = true)
            @RequestParam(value = "month", required = true) String month
    ){
        Log.info("getStatisticsByPlatAndMonth for plat" +  platID  +  " month: " + month +   "  begin");

        return true;
    }

    /**
     * 测试相关功能
     * @return
     */
    @RequestMapping("/test")
    public  String  test(){
        return  "XuehuStatisController RestController";
    }
//    http://localhost:8080/users/fpc
    @RequestMapping("/users/{username}")
    public String userProfile(@PathVariable String username){
//        return String.format("user %s", username);
        return "user PathVariable   " + username;
    }
}