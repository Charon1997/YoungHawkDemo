package com.charon.www.younghawkdemo.model;

import java.sql.Timestamp;

/**
 * @author waynezhang
 * @date 2017/8/10
 */
public class Discuss {

    private int dcId;
    private String title;
    private int userId;
    private UserBean userBean;
    private Timestamp time;
    private String dcContent;
    private int groupId;

    public int getGroupId() { return groupId; }

    public void setGroupId(int groupId) { this.groupId = groupId; }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDcId() {
        return dcId;
    }

    public void setDcId(int dcId) {
        this.dcId = dcId;
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

    public String getDcContent() {
        return dcContent;
    }

    public void setDcContent(String dcContent) {
        this.dcContent = dcContent;
    }
}
