package com.feri.ssm.service.impl;

import com.feri.ssm.dao.UserDao;
import com.feri.ssm.dao.UserSignDao;
import com.feri.ssm.entity.UserSign;
import com.feri.ssm.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:07
 */
@Service
public class UserSignServiceImpl implements UserSignService {
    @Autowired
    private UserSignDao dao;


    @Override
    public int save(UserSign sign) {
        return dao.save(sign);
    }
}
