package com.charon.www.younghawkdemo.temp;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charon on 2017/3/4.
 */
public class Date {
    public List<Team> teamList;

    private Man man00 = new Man("赵成超","项目牵头人","13897181363","157292534","157292534@qq.com");
    private Man man01 = new Man("钱志瀚","运营人员","15972861193","701370312","701370312@qq.com");
    private Man man02 = new Man("孙浩","后台开发工程师","18783552485","1173333196","1173333196@qq.com");
    private Man man03 = new Man("李承熹","iOS开发工程师","18785325978","1444762489","1444762489@qq.com");

    private Man man10 = new Man("周怀艺","项目牵头人","15920400069","366232635","366232635@qq.com");
    private Man man11 = new Man("吴心","产品经理","13458045927","832620384","832620384@qq.com");
    private Man man12 = new Man("郑冰冰","运营人员","13043140809","3163315627","3163315627@qq.com");
    private Man man13 = new Man("王八","后台开发工程师","13450405409","276203922","276203922@qq.com");

    private Man man20 = new Man("冯小明","项目牵头人","13101254188","128629130","128629130@qq.com");
    private Man man21 = new Man("陈冰","产品经理","15340504859","107846794","128629130@qq.com");
    private Man man22 = new Man("楚卫飞","运营人员","15040043358","255892305","128629130@qq.com");
    private Man man23 = new Man("卫炳昭","前端开发工程师","15408838340","150913305","128629130666@qq.com");
    private Man man24 = new Man("李雨豪","Android开发工程师","15404542978","127995471","128629130@qq.com");
    private Man man25 = new Man("施伟成","后台开发工程师","13983408082","102089364","128629130@qq.com");

//    private Man man30 = new Man("雷志瀚","牵头人","18883994289","641635716","lei.zhihan@nexuslink.cn");
//    private Man man31 = new Man("陈恳","项目指导","18983679652","670544040","chen.ken@nexuslink.cn");
//    private Man man32 = new Man("张怀艺","雏鹰学员","13883994162","814352016","zhang.huaiyi@foxmail.com");
//    private Man man33 = new Man("余承熹","雏鹰学员","18983065692","654617157","yu.chengxi@foxmail.com");
//    private Man man34 = new Man("税樱姿","雏鹰学员","13436012193","344156767","shui.yingzi@foxamil.com");
//    private Man man35 = new Man("邓成超","雏鹰学员","13452341835","782849471","deng.chengchao@foxmail.com");
//
//    private Man man40 = new Man("胡炳昭","牵头人","15340532851","978259812","hu.bingzhao@nexuslink.cn");
//    private Man man41 = new Man("袁瑞","项目指导","13540817427","1806415923","yuan.rui@nexuslink.cn");
//    private Man man42 = new Man("匡政泽","项目指导","13101280185","1284629130","kuang.zhengze@nexuslink.cn");
//    private Man man43 = new Man("李思佚","雏鹰学员","18725753168","335071345","li.siyi1@foxmail.com");
//    private Man man44 = new Man("余冰","雏鹰学员","18723541203","407506175","yu.bing2000@foxmail.com");
//
//    private Man man50 = new Man("陈思捷","牵头人","15922861093","707370312","chen.sijie@nexuslink.cn");
//    private Man man51 = new Man("高小明","项目指导","15310625455","1050561290","gao.xiaoming@nexuslink.cn");
//    private Man man52 = new Man("周鑫","雏鹰学员","13908310812","1138182119","zhou.xin0@foxmail.com");
//    private Man man53 = new Man("万雨豪","雏鹰学员","15320397740","1052934367","wanyuhao1@foxmail.com");
//    private Man man54 = new Man("周海林","雏鹰学员","13983064018","875773938","zhou.hailin1@foxmail.com");
//
//    private Man man60 = new Man("罗浩","牵头人","15923178431","863149924","luo.hao@nexuslink.cn");
//    private Man man61 = new Man("王义青","雏鹰学员","18725780633","1602806422","wang.yiqing99@foxmail.com");
//    private Man man62 = new Man("何金林","雏鹰学员","15202393232","1062031953","he.jinlin@foxmail.com");
//    private Man man63 = new Man("喻启洋","雏鹰学员","13110244037","1436484122","yu.qiyang@foxmail.com");

    public void addDate() {
        List<Man> manList0 = new ArrayList<>();
        manList0.add(man00);
        manList0.add(man01);
        manList0.add(man02);
        manList0.add(man03);
        Team team0 = new Team(manList0, "项目一");

        List<Man> manList1 = new ArrayList<>();
        manList1.add(man10);
        manList1.add(man11);
        manList1.add(man12);
        manList1.add(man13);
        Team team1 = new Team(manList1, "项目二");

        List<Man> manList2 = new ArrayList<>();
        manList2.add(man20);
        manList2.add(man21);
        manList2.add(man22);
        manList2.add(man23);
        manList2.add(man24);
        manList2.add(man25);
        Team team2 = new Team(manList2,"项目三");

//        List<Man> manList3 = new ArrayList<>();
//        manList3.add(man30);
//        manList3.add(man31);
//        manList3.add(man32);
//        manList3.add(man33);
//        manList3.add(man34);
//        manList3.add(man35);
//        Team team3 = new Team(manList3,"基于移动互联的硬件交互反馈系统");
//
//        List<Man> manList4 = new ArrayList<>();
//        manList4.add(man40);
//        manList4.add(man41);
//        manList4.add(man42);
//        manList4.add(man43);
//        manList4.add(man44);
//        Team team4 = new Team(manList4,"自行车行车安全导航仪");
//
//        List<Man> manList5 = new ArrayList<>();
//        manList5.add(man50);
//        manList5.add(man51);
//        manList5.add(man52);
//        manList5.add(man53);
//        manList5.add(man54);
//        Team team5 = new Team(manList5, "城市公厕资源调配系统");
//
//        List<Man> manList6 = new ArrayList<>();
//        manList6.add(man60);
//        manList6.add(man61);
//        manList6.add(man62);
//        manList6.add(man63);
//        Team team6 = new Team(manList6,"基于web的文件管理系统");

        teamList = new ArrayList<>();
        teamList.add(team0);
        teamList.add(team1);
        teamList.add(team2);
//        teamList.add(team3);
//        teamList.add(team4);
//        teamList.add(team5);
//        teamList.add(team6);
    }


}
