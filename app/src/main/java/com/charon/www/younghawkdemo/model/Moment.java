package com.charon.www.younghawkdemo.model;

import java.sql.Timestamp;

/**
 * @author wayne
 * @date 2017/8/10.
 */
public class Moment {

    private int userId;
    private Timestamp time;
    private int likes;
    private String content;
    private int momentId;
    private UserBean userBean;

    public Moment(int userId,String content){
        this.userId = userId;
        this.content = content;
    }

    public UserBean getUserBean() { return userBean; }

    public void setUserBean(UserBean userBean) { this.userBean = userBean; }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

}
