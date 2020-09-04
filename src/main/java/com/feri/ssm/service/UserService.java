package com.feri.ssm.service;

import com.feri.ssm.entity.User;
import com.feri.ssm.vo.LayuiTablePage;
import com.feri.ssm.vo.UserEcharts;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: Manager2003
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-09-02 14:06
 */
public interface UserService {
    int save(User user);
    int svaeBatch(List<User> userList);
    LayuiTablePage page(int page,int limit);
    UserEcharts tb();

    int batch(MultipartFile file) throws IOException;

    void download(int num, HttpServletResponse response) throws IOException;
}
