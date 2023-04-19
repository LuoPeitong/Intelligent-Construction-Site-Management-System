package com.lpt.service.Impl;

import com.lpt.dao.ILoginDao;
import com.lpt.dao.IStaffDao;
import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ILoginDao iLoginDao;

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public List<Login> findAll(){

        return iLoginDao.findAll();
    }

    @Override
    public Result login(Login login){

        Login responseLogin = iLoginDao.getByJobNo(login);
        // 判断是否存在用户
        if(responseLogin == null){

            return new Result(201,null,"该用户不存在");
        }
        int requestLogin = Integer.valueOf(login.getPermissions()).intValue();
        int responseLoginPer = Integer.valueOf(responseLogin.getPermissions()).intValue();
        // 比对权限
        if(requestLogin > responseLoginPer){

            return new Result(202,null,"无访问权限");
        }
        // 比对密码
        if(!responseLogin.getPwd().equals(login.getPwd())){

            return new Result(204,null,"密码错误");
        }
        iStaffDao.SetIsOnline(iStaffDao.getByJobNo(responseLogin.getJobNo()));
        Staff responseStaff = iStaffDao.getByJobNo(responseLogin.getJobNo());
        return new Result(200,responseStaff,"登录成功");
    }

    @Override
    public void add(Login user){

        iLoginDao.add(user);
    }
}
