package com.example.dao;

import com.example.bean.AnchorBaseInfoBean;

public interface AnchorBaseInfoDao {
    AnchorBaseInfoBean getAnchorBaseInfoByPlatRoom(int platform_id,String room_id);
}