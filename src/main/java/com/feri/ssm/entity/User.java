package com.feri.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 11:42
 */
@Data
public class User {
    private Integer id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //接收
    @JsonFormat(pattern = "yyyy-MM-dd") //返回
    private Date birthday;
    private Integer sex;
    private String dept;
    private String job;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //返回
    private Date ctime;
}