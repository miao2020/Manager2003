package com.feri.ssm.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 15:05
 */
@Data
public class LayuiTablePage {
    private int code;
    private int count;
    private String msg;
    private Object data;
}