package com.lpt.service;

import com.lpt.pojo.Login;
import com.lpt.result.Result;

import java.util.List;

public interface LoginService {

    List<Login> findAll();

    Result login(Login login);

    void add(Login login);

    Result editPwd(Login login);
}
