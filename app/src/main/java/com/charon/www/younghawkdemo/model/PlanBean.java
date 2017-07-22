package com.charon.www.younghawkdemo.model;

/**
 * Created by Administrator on 2017/4/26.
 */

public class PlanBean {
    private String userName;
    private Time pubTime;
    private String summary;
    private String plan;

    public PlanBean(String userName, Time pubTime, String summary, String plan) {
        this.userName = userName;
        this.pubTime = pubTime;
        this.summary = summary;
        this.plan = plan;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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


}
