package com.feri.ssm.service.impl;

import com.feri.ssm.dao.UserDao;
import com.feri.ssm.dto.UserDept;
import com.feri.ssm.entity.User;
import com.feri.ssm.service.UserService;
import com.feri.ssm.vo.LayuiTablePage;
import com.feri.ssm.vo.UserEcharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:07
 */
@Service
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
}
