package com.charon.www.younghawkdemo.model;

import java.sql.Timestamp;

/**
 * @author wayne
 * @data 2017/08/10
 */
public class Plan {

    private String userName;
    private int userId;
    private Timestamp time;
    private String planContent;
    private String sumContent;
    private int planId;
    private UserBean userBean;

    public UserBean getUserBean() { return userBean; }

    public void setUserBean(UserBean userBean) { this.userBean = userBean; }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getSumContent() {
        return sumContent;
    }

    public void setSumContent(String sumContent) {
        this.sumContent = sumContent;
    }

}
