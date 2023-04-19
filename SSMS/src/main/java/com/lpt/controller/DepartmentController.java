package com.lpt.controller;

import com.lpt.result.Result;
import com.lpt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/department/getList")
    public Result getList(){

        return new Result(200,departmentService.findAll(),"获取部门列表");
    }

}
