package com.feri.ssm.dao;

import com.feri.ssm.dto.UserDept;
import com.feri.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 11:52
 */
public interface UserDao {
    //新增
    int save(User user);
    //批量新增
    int saveBatch(List<User> userList);
    //查询数量
    int selectCount();
    List<User> selectPage(@Param("s") int s,@Param("c") int c);

    //查询统计部门人数
    List<UserDept> depts();
}
