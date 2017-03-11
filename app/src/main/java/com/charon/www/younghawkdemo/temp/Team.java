package com.charon.www.younghawkdemo.temp;


import java.util.List;

/**
 * Created by Chaorn on 2017/3/8.
 */
public class Team {
    private List<Man> manList;
    private String projectName;

    Team(List<Man> manList, String projectName) {
        this.manList = manList;
        this.projectName = projectName;
    }
    public List<Man> getManList() {
        return manList;
    }

    public void setManList(List<Man> manList) {
        this.manList = manList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
