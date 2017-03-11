package com.charon.www.younghawkdemo.temp;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/8.
 */
public class Man implements Serializable {
    private String name;
    private String duty;
    private String phoneNum;
    private String QQNum;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getQQNum() {
        return QQNum;
    }

    public void setQQNum(String QQNum) {
        this.QQNum = QQNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Man() {

    }
    public   Man(String name, String duty, String phoneNum, String QQNum, String email) {
        this.name = name;
        this.duty = duty;
        this.phoneNum = phoneNum;
        this.QQNum = QQNum;
        this.email = email;
    }
}
