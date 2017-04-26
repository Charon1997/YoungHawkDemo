package com.charon.www.younghawkdemo.model;

/**
 * Created by Administrator on 2017/4/26.
 */

public class PlanItem {
    private String userName;
    private Time pubTime;
    private String pubContent;

    public PlanItem(String userName, Time pubTime, String pubContent) {
        this.userName = userName;
        this.pubTime = pubTime;
        this.pubContent = pubContent;
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
