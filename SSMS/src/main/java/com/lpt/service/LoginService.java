package com.lpt.service;

import com.lpt.pojo.Login;
import com.lpt.result.Result;

import java.util.List;

public interface LoginService {

    public List<Login> findAll();

    public Result login(Login login);

    public void add(Login login);
}
