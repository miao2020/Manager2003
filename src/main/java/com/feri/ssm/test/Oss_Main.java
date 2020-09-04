package com.feri.ssm.test;

import com.feri.ssm.oss.OssUtil;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-04 11:02
 */
public class Oss_Main {
    public static void main(String[] args) {
        String url=OssUtil.upload("str.json","{\"id\":101,\"name\":\"Java\"}");
        System.out.println(url);
    }
}
