package com.feri.ssm.controller;

import com.feri.ssm.dto.TestDto;
import com.feri.ssm.entity.UserSign;
import com.feri.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-03 09:32
 */
@Controller
public class TestController {
    @GetMapping("test.do")
    public String ok(@DateTimeFormat(pattern = "yyyy-MM-dd")Date cdate){
        System.out.println(cdate);
        return "OK";
    }
    @GetMapping("test2.do")
    public String ok(TestDto dto){
        System.out.println(dto);
        return "OK";
    }
    @Autowired
    private UserService service;
    //文件下载 数据导出
    @RequestMapping(value = "user/down.do",method = {RequestMethod.GET})
    public void down(int num, HttpServletResponse response) throws IOException {
        service.download(num, response);
    }
}
