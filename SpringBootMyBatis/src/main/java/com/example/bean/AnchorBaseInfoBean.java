package com.example.bean;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: sunxianpeng
 * \* Date: 2019/8/11
 * \* Time: 20:25
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class AnchorBaseInfoBean {
    private int platform_id;
    private String room_id;
    private  String sex;

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AnchorBaseInfoBean{" +
                "platform_id=" + platform_id +
                ", room_id='" + room_id + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}