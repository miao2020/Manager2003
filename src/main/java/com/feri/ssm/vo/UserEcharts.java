package com.feri.ssm.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 15:55
 */
@Data
public class UserEcharts {
    private List<String> depts;
    private List<Integer> cts;
}
