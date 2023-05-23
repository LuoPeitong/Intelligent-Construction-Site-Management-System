package com.lpt.controller;

import com.lpt.pojo.Department;
import com.lpt.result.Result;
import com.lpt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping(value = "/department/getList")
    public Result getList(){
        try{

            return departmentService.findAll();
        }
        catch (Exception e){
            return new Result(400,null,"出错了");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/department/editDepartment")
    public Result editDepartment(@RequestBody Department department){
        try{

            return departmentService.editDepartment(department);
        }
        catch (Exception e){

            return new Result(400,null,"请求失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/department/delDepartment")
    public Result delDepartment(@RequestBody Department department){
        try{
            return departmentService.delDepartment(department);
        }
        catch (Exception e){

            return new Result(400,null,"请求失败");
        }
    }
}
