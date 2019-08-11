package com.example.controller;


import com.example.bean.AnchorBaseInfoBean;
import com.example.dao.AnchorBaseInfoDao;
import com.example.service.AnchorBaseInfoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/anchor", produces = "application/json; charset=UTF-8")
public class AnchorBaseInfoController {
 
    @Autowired
    private AnchorBaseInfoService anchorBaseInfoService;
    @Autowired
    private AnchorBaseInfoDao anchorBaseInfoDao;
//  http://localhost:8080/anchor/getTodayBaseInfoByPlatRoomTimeHorizon?platID=2&roomID=1023513
    @RequestMapping(value = "/getTodayBaseInfoByPlatRoomTimeHorizon", method = {RequestMethod.GET})
    public AnchorBaseInfoBean getAnchorBaseInfoByPlatRoom(
            @ApiParam(value = "平台ID", required = true)  @RequestParam(value = "platID", required = true) int platID,
            @ApiParam(value = "房间ID", required = true)  @RequestParam(value = "roomID", required = true) String roomID
    ) {

        AnchorBaseInfoBean resultBean=anchorBaseInfoDao.getAnchorBaseInfoByPlatRoom(platID,roomID);

        System.out.println(resultBean);
        return  resultBean;
    }
 
}