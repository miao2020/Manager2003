package com.feri.ssm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private int code;
    private String msg;
    private Object data;
}
