package com.charon.www.younghawkdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/3/4.
 */
public class Date {
    public List<Team> teamList;

    Man man00 = new Man("张挺","运营专责","13896131363","957291234","zhang.ting@nexuslink.cn");
    Man man01 = new Man("陈思捷","运营专责","15922861093","707370312","chen.sijie@nexuslink.cn");
    Man man02 = new Man("杨键","运营专责","18883992485","1179903196","yang.jian@nexuslink.cn");
    Man man03 = new Man("张文梁","运营专责","17784325978","1024762489","zhang.wenliang@nexuslink.cn");

    Man man10 = new Man("瞿强","中学指导","15922679569","366237235","366237235@qq.com");
    Man man11 = new Man("李艺","中学指导","13983905927","83241034","83241034@qq.com");
    Man man12 = new Man("李才猛","中学指导","13883140809","316331557","316331557@qq.com");
    Man man13 = new Man("马宪羽","中学指导","13452905409","274030922","274030922@qq.com");

    Man man20 = new Man("匡政泽","牵头人","13101280185","1284629130","kuang.zhengze@nexuslink.cn");
    Man man21 = new Man("周家豪","项目指导","15340504859","1078463794","zhou.jiahao@nexuslink.cn");
    Man man22 = new Man("张兴锐","项目指导","15086943358","2855892305","zhang.xingrui@nexuslink.cn");
    Man man23 = new Man("黄嘉豪","雏鹰学员","15922838340","1509173305","huang.jiahao666@foxmail.com");
    Man man24 = new Man("李云飞","雏鹰学员","15922542978","1275995471","li.yunfei88@foxmail.com");
    Man man25 = new Man("施伟成","雏鹰学员","13983282082","1092089364","shi.weicheng@foxmail.com");

    Man man30 = new Man("雷志瀚","牵头人","18883994289","641635716","lei.zhihan@nexuslink.cn");
    Man man31 = new Man("陈恳","项目指导","18983679652","670544040","chen.ken@nexuslink.cn");
    Man man32 = new Man("张怀艺","雏鹰学员","13883994162","814352016","zhang.huaiyi@foxmail.com");
    Man man33 = new Man("余承熹","雏鹰学员","18983065692","654617157","yu.chengxi@foxmail.com");
    Man man34 = new Man("税樱姿","雏鹰学员","13436012193","344156767","shui.yingzi@foxamil.com");
    Man man35 = new Man("邓成超","雏鹰学员","13452341835","782849471","deng.chengchao@foxmail.com");

    Man man40 = new Man("胡炳昭","牵头人","15340532851","978259812","hu.bingzhao@nexuslink.cn");
    Man man41 = new Man("袁瑞","项目指导","13540817427","1806415923","yuan.rui@nexuslink.cn");
    Man man42 = new Man("匡政泽","项目指导","13101280185","1284629130","kuang.zhengze@nexuslink.cn");
    Man man43 = new Man("李思佚","雏鹰学员","18725753168","335071345","li.siyi1@foxmail.com");
    Man man44 = new Man("余冰","雏鹰学员","18723541203","407506175","yu.bing2000@foxmail.com");

    Man man50 = new Man("陈思捷","牵头人","15922861093","707370312","chen.sijie@nexuslink.cn");
    Man man51 = new Man("高小明","项目指导","15310625455","1050561290","gao.xiaoming@nexuslink.cn");
    Man man52 = new Man("周鑫","雏鹰学员","13908310812","1138182119","zhou.xin0@foxmail.com");
    Man man53 = new Man("万雨豪","雏鹰学员","15320397740","1052934367","wanyuhao1@foxmail.com");
    Man man54 = new Man("周海林","雏鹰学员","13983064018","875773938","zhou.hailin1@foxmail.com");

    Man man60 = new Man("罗浩","牵头人","15923178431","863149924","luo.hao@nexuslink.cn");
    Man man61 = new Man("王义青","雏鹰学员","18725780633","1602806422","wang.yiqing99@foxmail.com");
    Man man62 = new Man("何金林","雏鹰学员","15202393232","1062031953","he.jinlin@foxmail.com");
    Man man63 = new Man("喻启洋","雏鹰学员","13110244037","1436484122","yu.qiyang@foxmail.com");

    public void addDate() {
        List<Man> manList0 = new ArrayList<>();
        manList0.add(man00);
        manList0.add(man01);
        manList0.add(man02);
        manList0.add(man03);
        Team team0 = new Team(manList0, "运营专责");

        List<Man> manList1 = new ArrayList<>();
        manList1.add(man10);
        manList1.add(man11);
        manList1.add(man12);
        manList1.add(man13);
        Team team1 = new Team(manList1, "中学指导");

        List<Man> manList2 = new ArrayList<>();
        manList2.add(man20);
        manList2.add(man21);
        manList2.add(man22);
        manList2.add(man23);
        manList2.add(man24);
        manList2.add(man25);
        Team team2 = new Team(manList2,"智能运动终端");

        List<Man> manList3 = new ArrayList<>();
        manList3.add(man30);
        manList3.add(man31);
        manList3.add(man32);
        manList3.add(man33);
        manList3.add(man34);
        manList3.add(man35);
        Team team3 = new Team(manList3,"基于移动互联的硬件交互反馈系统");

        List<Man> manList4 = new ArrayList<>();
        manList4.add(man40);
        manList4.add(man41);
        manList4.add(man42);
        manList4.add(man43);
        manList4.add(man44);
        Team team4 = new Team(manList4,"自行车行车安全导航仪");

        List<Man> manList5 = new ArrayList<>();
        manList5.add(man50);
        manList5.add(man51);
        manList5.add(man52);
        manList5.add(man53);
        manList5.add(man54);
        Team team5 = new Team(manList5, "城市公厕资源调配系统");

        List<Man> manList6 = new ArrayList<>();
        manList6.add(man60);
        manList6.add(man61);
        manList6.add(man62);
        manList6.add(man63);
        Team team6 = new Team(manList6,"基于web的文件管理系统");

        teamList = new ArrayList<>();
        teamList.add(team0);
        teamList.add(team1);
        teamList.add(team2);
        teamList.add(team3);
        teamList.add(team4);
        teamList.add(team5);
        teamList.add(team6);
    }


}
