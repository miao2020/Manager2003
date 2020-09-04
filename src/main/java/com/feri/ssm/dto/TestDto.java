package com.feri.ssm.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-03 10:03
 */
@Data
public class TestDto {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ctime;
}
