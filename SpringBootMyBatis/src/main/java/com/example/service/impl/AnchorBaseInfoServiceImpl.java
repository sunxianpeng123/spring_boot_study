package com.example.service.impl;

import com.example.bean.AnchorBaseInfoBean;
import com.example.dao.AnchorBaseInfoDao;
import com.example.service.AnchorBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnchorBaseInfoServiceImpl implements AnchorBaseInfoService {
 
    @Autowired
    private AnchorBaseInfoDao anchorBaseInfoDao;


    @Override
    public AnchorBaseInfoBean getAnchorBaseInfoBean(int platform_id,String room_id)
    {
        AnchorBaseInfoBean anchorBaseInfoBean=anchorBaseInfoDao.getAnchorBaseInfoByPlatRoom(platform_id,room_id);
        return anchorBaseInfoBean;
    }
}
