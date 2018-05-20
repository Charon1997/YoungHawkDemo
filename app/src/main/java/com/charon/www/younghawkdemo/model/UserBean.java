package com.charon.www.younghawkdemo.model;

/**
 * @author
 * @date 2017/8/10
 */
public class UserBean {

    private int userId;
    private String userName;
    private String avatarUrl;
    private String tel;
    private String school;
    private int age;
    private String gender;
    private String pwd;
    /**
     * 0 挺哥 1我们 2雏鹰 3游客
     */
    private int identity;
    private String qqNum;
    private String eMail;
    private String job;
    private int groupId;

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getAvatarUrl() { return avatarUrl; }

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getTel() { return tel; }

    public void setTel(String tel) { this.tel = tel;}

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
