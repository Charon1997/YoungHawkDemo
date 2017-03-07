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
    private String man00[] = new String[]{"张挺","运营专责","13896131363","957291234","zhang.ting@nexuslink.cn"};
    private String man01[] = new String[]{"张延伟","运营专责","18883880498","1548983536","zhang.yanwei@nexuslink.cn"};
    private String man02[] = new String[]{"马戎","运营专责","18883864984","253727942","ma.rong@nexuslink.cn"};
    private String man10[] = new String[]{"瞿强","中学指导","15922679569","366237235","366237235@qq.com"};
    private String man11[] = new String[]{"李艺","中学指导","13983905927","83241034","83241034@qq.com"};
    private String man20[] = new String[]{"刘乔宇","牵头人","18883848897","756526597","liu.qiaoyu@nexuslink.cn"};
    private String man21[] = new String[]{"钱虹","项目指导","18996387494","1099634723","qian.hong@nexuslink.cn"};
    private String man22[] = new String[]{"学员A","雏鹰学员","111111111","5203698741","5203698741@qq.com"};

    public void addToTeam() {
        teamList0 = new ArrayList<>();
        teamList0.add(man00);
        teamList0.add(man01);
        teamList0.add(man02);
        teamList1 = new ArrayList<>();
        teamList1.add(man10);
        teamList1.add(man11);
        teamList2 = new ArrayList<>();
        teamList2.add(man20);
        teamList2.add(man21);
        teamList2.add(man22);

        projectList = new ArrayList<>();
        projectList.add(teamList0);
        projectList.add(teamList1);
        projectList.add(teamList2);
    }

}
