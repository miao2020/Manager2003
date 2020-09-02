package com.feri.ssm.controller;

import com.feri.ssm.entity.UserSign;
import com.feri.ssm.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:28
 */
@RestController
public class UserSignController {
    @Autowired
    private UserSignService signService;

    @RequestMapping("/user/sign.do")
    public int save(UserSign sign){
        return signService.save(sign);
    }
}
