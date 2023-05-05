package com.lpt.service;

import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.result.pojo.RequestStaff;

import javax.mail.MessagingException;
import java.util.List;

public interface LoginService {

    List<Login> findAll();

    Result login(Login login);

    void add(Login login);

    Result editPwd(Login login);

    Result getCode(Staff staff) throws MessagingException;

    Result resetPwd(RequestStaff r);
}
