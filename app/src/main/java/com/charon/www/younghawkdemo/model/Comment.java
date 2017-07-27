package com.charon.www.younghawkdemo.model;

/**
 * Created by Administrator on 2017/7/26.
 */

public class Comment {
    private int ID;//  评论ID
    private String content;//  评论内容
    private int UserId;//  用户ID

    public Comment(int ID, String content, int userId) {
        this.ID = ID;
        this.content = content;
        UserId = userId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
