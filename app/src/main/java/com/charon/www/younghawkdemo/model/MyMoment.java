package com.charon.www.younghawkdemo.model;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/5/20 11:38
 * 修改人：Charon
 * 修改时间：2018/5/20 11:38
 * 修改备注：
 */

public class MyMoment {

    /**
     * userId : 2
     * time : Jan 1, 1970 8:00:00 AM
     * likes : 0
     * content : 动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态动态
     * momentId : 1
     * userBean : {"userId":2,"userName":"user2","avatarUrl":"http://47.93.231.115:8080/eyas_war/profilePhotos/default.jpg","tel":"1234","school":"重庆邮电大学","age":20,"gender":"男","identity":1,"qqNum":"1024762489","eMail":"email@email.cn","job":"运营专责","groupId":0}
     */

    private int userId;
    private String time;
    private int likes;
    private String content;
    private int momentId;
    private UserBeanBean userBean;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMomentId() {
        return momentId;
    }

    public void setMomentId(int momentId) {
        this.momentId = momentId;
    }

    public UserBeanBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBeanBean userBean) {
        this.userBean = userBean;
    }

    public static class UserBeanBean {
        /**
         * userId : 2
         * userName : user2
         * avatarUrl : http://47.93.231.115:8080/eyas_war/profilePhotos/default.jpg
         * tel : 1234
         * school : 重庆邮电大学
         * age : 20
         * gender : 男
         * identity : 1
         * qqNum : 1024762489
         * eMail : email@email.cn
         * job : 运营专责
         * groupId : 0
         */

        private int userId;
        private String userName;
        private String avatarUrl;
        private String tel;
        private String school;
        private int age;
        private String gender;
        private int identity;
        private String qqNum;
        private String eMail;
        private String job;
        private int groupId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public String getQqNum() {
            return qqNum;
        }

        public void setQqNum(String qqNum) {
            this.qqNum = qqNum;
        }

        public String getEMail() {
            return eMail;
        }

        public void setEMail(String eMail) {
            this.eMail = eMail;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }
    }
}
