package com.lpt.controller;

import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService staffService;

    @CrossOrigin
    @RequestMapping(value = "/staff/getAllStaff")
    @ResponseBody
    public Result getAllByProject() {

        return new Result(200, staffService.findAll(),"group by project");
    }

    @CrossOrigin
    @RequestMapping(value = "/staff/edit")
    @ResponseBody
    public Result edit(@RequestBody Staff staff) {

        try{
            staffService.editStaff(staff);
            return new Result(200, null,"编辑成功");
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400, null,"出错了");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/staff/delete")
    @ResponseBody
    public Result deleteStaff(@RequestBody Staff staff) {

        // System.out.println(staff.toString());
        staffService.deleteStaff(staff);
        return new Result(200, null,"删除成功");
    }
}
