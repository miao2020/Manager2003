package com.feri.ssm.test;

import com.feri.ssm.entity.User;
import com.feri.ssm.util.EmailUtil;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-03 15:14
 */
public class Email_Main {
    public static void main(String[] args) {
//        boolean r=EmailUtil.sendEmail("hiprogramming@foxmail.com","来自Java2003的问候","嗨，下课吧！<h1>快来点击<a href='https://www.juhe.cn/'>https://www.juhe.cn/</h1>");
//        System.out.println(r);
        System.out.println(ad());

    }
    public static int ad(){
        int i=10;
        try{
            System.exit(1);
            return ++i;
        }finally {
            ++i;

            throw new NullPointerException("啊哈哈哈");
            //System.out.println(i);
            //return i;
        }
    }

    @Override
    protected void finalize() throws Throwable {
//        SoftReference<User> reference=new SoftReference<>(new User());
//        reference.get();
//        WeakReference<User> reference1=new WeakReference<>(new User(),new ReferenceQueue<>());
//        reference1.get();
//        PhantomReference<User> reference2=new PhantomReference<>();

    }
}
