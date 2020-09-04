package com.feri.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 11:42
 */
@Data
public class UserSign {
    private Integer id;
    private Integer uid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //返回
    private Date starttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //返回
    private Date endtime;
    private Integer flag;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //返回
    private Date ctime;
}
