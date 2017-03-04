package com.charon.www.younghawkdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 */
public class Date {
    private List<String[]> teamList0;
    private List<String[]> teamList1;
    private List<String[]> teamList2;
    public List<List<String[]>> projectList;
    private String man00[] = new String[]{"tom","项目指导","18983679652","670544040","670544040@qq.com"};
    private String man01[] = new String[]{"tim","雏鹰学员","1898323352","12344040","chen.ken@nexuslink.cn"};
    private String man10[] = new String[]{"charon","牵头人","182832652","6712444040","chenken97@foxmail.com"};
    private String man11[] = new String[]{"pluto","雏鹰学员","18183679652","12340544040","548@qq4.com"};
    private String man20[] = new String[]{"jim","牵头人","1123679652","34402","54d8@qq5.com"};
    private String man21[] = new String[]{"henry","项目指导","11239652","67123401230","548@qq6.com"};

    public void addToTeam() {
        teamList0 = new ArrayList<>();
        teamList0.add(man00);
        teamList0.add(man01);
        teamList1 = new ArrayList<>();
        teamList1.add(man10);
        teamList1.add(man11);
        teamList2 = new ArrayList<>();
        teamList2.add(man20);
        teamList2.add(man21);

        projectList = new ArrayList<>();
        projectList.add(teamList0);
        projectList.add(teamList1);
        projectList.add(teamList2);
    }

}
