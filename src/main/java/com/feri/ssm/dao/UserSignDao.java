package com.feri.ssm.dao;

import com.feri.ssm.entity.UserSign;
import org.apache.ibatis.annotations.Insert;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 11:54
 */
public interface UserSignDao {
    @Insert("insert inro t_usersign(uid,starttime,endtime,flag,address,ctime ) values(#{uid},#{starttime},null,#{flag},#{address},now())")
    int save(UserSign sign);

}
