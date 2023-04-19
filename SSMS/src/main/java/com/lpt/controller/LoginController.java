package com.lpt.controller;

import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.service.LoginService;
import com.lpt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private StaffService staffService;

    @CrossOrigin
    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody Login requestLogin, HttpServletRequest request) {

        Result result = loginService.login(requestLogin);
        // 登录成功，存session
        if(result.getCode()==200){
            HttpSession session = request.getSession(true);
            session.setAttribute("staff",result.getObject());
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/logOut")
    @ResponseBody
    public Result logOut(@RequestBody Staff staff, HttpServletRequest request) {

        staffService.SetNotOnline(staff);
        HttpSession session = request.getSession(true);
        session.removeAttribute("staff");
        return new Result(200,null,"成功退出");
    }

    @CrossOrigin
    @RequestMapping(value = "/user/findAll")
    @ResponseBody
    public List<Login> findAll() {

        return loginService.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/user/add")
    @ResponseBody
    public void add(@RequestBody Login requestLogin) {

        loginService.add(requestLogin);
    }

}
