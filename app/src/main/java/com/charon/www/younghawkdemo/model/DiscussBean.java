package com.charon.www.younghawkdemo.model;

/**
 * Created by Administrator on 2017/7/21.
 */

public class DiscussBean {
    private String userName;
    private Time pubTime;
    private String pubContent;
    private String pubTitle;

    public DiscussBean(String userName, Time pubTime, String pubContent,String pubTitle) {
        this.userName = userName;
        this.pubTime = pubTime;
        this.pubContent = pubContent;
        this.pubTitle = pubTitle;
    }

    public String getPubTitle() {
        return pubTitle;
    }

    public void setPubTitle(String pubTitle) {
        this.pubTitle = pubTitle;
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
}
