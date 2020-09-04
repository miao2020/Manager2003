package com.feri.ssm.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @program: Manager2003
 * @description: 基于java的邮箱  实现邮箱发送
 * @author: Feri(邢朋辉)
 * @create: 2020-09-03 14:54
 */
public class EmailUtil {

    public static boolean sendEmail(String email,String sub,String content)  {
        //1.设置常规信息  Map集合的子类  可以持久化
        Properties properties=new Properties();
        //开启邮箱认证
        properties.put("mail.smtp.auth",true);
        //设置邮箱服务器
        properties.put("mail.host","smtp.163.com");
        //创建授权对象
        Authenticator authenticator=new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("zzjava168@163.com","RQWVRNHSMRYFFZHR");
            }
        };
        //创建会话对象
        Session session=Session.getDefaultInstance(properties,authenticator);
        //创建消息对象
        Message message=new MimeMessage(session);
        try {
            //设置发件人
            message.setFrom(new InternetAddress("zzjava168@163.com"));
            //设置接收人
            /**
             * 接收人有三种：
             * 1.收件人 RecipientType.TO
             * 2.抄送人 RecipientType.CC
             * 3.密送人 RecipientType.BCC
             * */
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //设置邮箱标题
            message.setSubject(sub);
            //设置邮箱的正文内容
            //message.setText();
            message.setContent(content, "text/html;charset=UTF-8");


            //发送邮件
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
