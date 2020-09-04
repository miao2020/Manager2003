package com.feri.ssm.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.feri.ssm.entity.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-03 10:53
 */
public class EasyExcel_Main {
    public static void main(String[] args) {
        //生成excel
        List<User> list1=new ArrayList<>();

        for(int i=1;i<2000;i++){
            User user=new User();
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.YEAR,-i%20);
            user.setBirthday(calendar.getTime());
            user.setDept("财务部");
            user.setJob("初级程序员");
            user.setName("高斯林-"+i);
            user.setSex(2);
            list1.add(user);
        }
        /**
         * write:1.文件路径和名称 2.要生成的数据的Class对象
         * sheet：设置Sheet的名称
         * doWrite：写出内容 传递要写出的集合*/
        EasyExcel.write("users.xlsx",User.class).sheet("第一个").doWrite(list1);

        //解析excel
        List<User> list=EasyExcel.read("users.xlsx").sheet().head(User.class).doReadSync();
        System.out.println(list);
    }
}