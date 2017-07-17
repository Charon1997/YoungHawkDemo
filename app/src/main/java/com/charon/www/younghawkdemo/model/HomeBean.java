package com.charon.www.younghawkdemo.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class HomeBean {
    private int img;
    private String userName;
    private Time pubTime;
    private String pubContent;
    private int likeNum;
    private int commentNum;

    public HomeBean(int img, String userName, Time pubTime, String pubContent, int likeNum, int commentNum) {
        this.img = img;
        this.userName = userName;
        this.pubTime = pubTime;
        this.pubContent = pubContent;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Time getPubTime() {
        return pubTime;
    }

    public void setPubTime(Time pubTime) {
        this.pubTime = pubTime;
    }

    public String getPubContent() {
        return pubContent;
    }

    public void setPubContent(String pubContent) {
        this.pubContent = pubContent;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
