package com.feri.ssm.service.impl;

import com.alibaba.excel.EasyExcel;
import com.feri.ssm.dao.UserDao;
import com.feri.ssm.dto.UserDept;
import com.feri.ssm.entity.User;
import com.feri.ssm.service.UserService;
import com.feri.ssm.vo.LayuiTablePage;
import com.feri.ssm.vo.UserEcharts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:07
 */
@Service
@Slf4j //基于Slf4j记录日志
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Override
    public int save(User user) {
        return dao.save(user);
    }

    @Override
    public int svaeBatch(List<User> userList) {
        return dao.saveBatch(userList);
    }

    @Override
    public LayuiTablePage page(int page, int limit) {
        LayuiTablePage vo=new LayuiTablePage();
        vo.setCode(0);
        vo.setMsg("OK");
        vo.setCount(dao.selectCount());

        vo.setData(dao.selectPage((page-1)*limit, limit));
        return vo;
    }

    @Override
    public UserEcharts tb() {
        List<UserDept> list=dao.depts();
        UserEcharts ue=new UserEcharts();
        ue.setCts(new ArrayList<>());
        ue.setDepts(new ArrayList<>());
        for(UserDept d:list){
            ue.getCts().add(d.getCt());
            ue.getDepts().add(d.getDept());
        }
        return ue;
    }

    @Override
    public int batch(MultipartFile file) throws IOException {
        //验证 非空
        if(!file.isEmpty()){
            //解析结果 同步解析
            List<User> list=EasyExcel.read(file.getInputStream()).sheet().head(User.class).doReadSync();
            //验证读取的结果
            if(list!=null && list.size()>0){
                System.out.println(list);
                log.info("读取结果："+list.size());
                //操作数据库实现批处理
                return dao.saveBatch(list);
            }
        }
        return -1;
    }

    @Override
    public void download(int num, HttpServletResponse response) throws IOException {
        //1.查询要生成的内容
        List<User> list=dao.selectNum(num);
        //2.设置下载 需要设置响应消息头
        //application/octet-stream 二进制 下载
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        //设置下载的文件名称
        response.setHeader("Content-Disposition","attachment;filename=users.xlsx");
        //将要下载的内容转换为Excel格式，并且写出
        EasyExcel.write(response.getOutputStream(),User.class).sheet("系统自动导出"+list.size()).doWrite(list);
    }
}
