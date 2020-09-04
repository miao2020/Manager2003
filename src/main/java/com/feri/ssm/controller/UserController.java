package com.feri.ssm.controller;

import com.aliyun.oss.internal.OSSUtils;
import com.feri.ssm.entity.User;
import com.feri.ssm.entity.UserSign;
import com.feri.ssm.oss.OssUtil;
import com.feri.ssm.service.UserService;
import com.feri.ssm.vo.LayuiTablePage;
import com.feri.ssm.vo.R;
import com.feri.ssm.vo.UserEcharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:10
 */
//@Controller // 实现接口映射
@RestController  //等价于 @Controller+@ResponseBody
public class UserController {
    @Autowired
//    @Qualifier(value = "")
//    @Resource(name = "")
    private UserService service;
    @RequestMapping(value = "user/save.do",method = {RequestMethod.POST})
    public int save(User user){
        return service.save(user);
    }



    @RequestMapping("user/list.do/{count}")
    public List<User> get(@PathVariable int count){
        return null;
    }
    @CrossOrigin //允许跨域
    @RequestMapping("/user/page.do")
    public LayuiTablePage page(int page,int limit){
        return service.page(page, limit);
    }
    @CrossOrigin
    @RequestMapping("user/echarts.do")
    public UserEcharts tb(){
        return service.tb();
    }

    //    @PostMapping("")  post请求
//    @GetMapping("")   get请求
//    @PutMapping("")   put请求
//    @DeleteMapping("")  delete请求

    //文件上传  必须post请求
    @RequestMapping(value = "user/batch.do",method = {RequestMethod.POST})
    public int batch(MultipartFile file) throws IOException {
        return service.batch(file);
    }
    //
    @RequestMapping(value = "user/upload.do",method = {RequestMethod.POST})
    public R upload(MultipartFile file) throws IOException {
        //验证
        if(!file.isEmpty()){
            //基于OSS实现上传
            String url= OssUtil.upload(OssUtil.rename(file.getOriginalFilename()),file.getBytes());
            return new R(0,"",url);
        }else {
            return new R(1,"请选择上传的文件",null);
        }
    }

}
