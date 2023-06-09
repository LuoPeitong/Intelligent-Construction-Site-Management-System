package com.lpt.controller;

import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/staff/getAllStaff")
    @ResponseBody
    public Result getAllByProject() {

        return new Result(200, staffService.findAll(),"group by project");
    }

    @RequestMapping(value = "/staff/edit")
    @ResponseBody
    public Result edit(@RequestBody Staff staff) {

        try{

            return staffService.editStaff(staff);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400, null,"出错了");
        }
    }

    @RequestMapping(value = "/staff/delete")
    @ResponseBody
    public Result deleteStaff(@RequestBody Staff staff) {

        try{

            return staffService.deleteStaff(staff);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400, null,"出错了");
        }
    }

    @RequestMapping(value = "/staff/queryPerReport")
    @ResponseBody
    public Result queryPerReport() {
        try{

            return staffService.queryPerReport();
        }
        catch (Exception e){
            return new Result(400, null,"出错了");
        }
    }
}
