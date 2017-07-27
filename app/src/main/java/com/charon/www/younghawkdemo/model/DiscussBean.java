package com.charon.www.younghawkdemo.model;

/**
 * Created by Charon on 2017/7/21.
 */

public class DiscussBean {
    private String userName;
    private Date pubTime;
    private String pubContent;
    private String pubTitle;

    /**
     * @param userName
     * @param pubTime
     * @param pubContent
     * @param pubTitle
     */
    public DiscussBean(String userName, Date pubTime, String pubContent,String pubTitle) {
        this.userName = userName;
        this.pubTime = pubTime;
        this.pubContent = pubContent;
        this.pubTitle = pubTitle;
    }

    /**
     * @return
     */
    public String getPubTitle() {
        return pubTitle;
    }

    /**
     * @param pubTitle
     */
    public void setPubTitle(String pubTitle) {
        this.pubTitle = pubTitle;
    }

    /**
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return
     */
    public Date getPubTime() {
        return pubTime;
    }

    /**
     * @param pubTime
     */
    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    /**
     * @return pubContent
     */
    public String getPubContent() {
        return pubContent;
    }

    /**
     * @param pubContent
     */
    public void setPubContent(String pubContent) {
        this.pubContent = pubContent;
    }


}
