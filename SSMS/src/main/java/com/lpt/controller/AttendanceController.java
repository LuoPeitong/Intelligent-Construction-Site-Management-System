package com.lpt.controller;

import com.lpt.pojo.Attendance;
import com.lpt.result.Result;
import com.lpt.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/findAll")
    public Result findAll(){

        return new Result(200,attendanceService.findAll(),"成功获取考勤列表");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/export")
    public Result export() throws IOException {

        attendanceService.export();
        return new Result(200,null,"员工考勤信息导出成功");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/findData")
    public Result findOriginal(@RequestBody Attendance attendance) {

        return new Result(200,attendanceService.findData(attendance),"获取成功");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/signIn")
    public Result signIn(@RequestBody Attendance attendance) {

        return attendanceService.signIn(attendance);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/signOut")
    public Result signOut(@RequestBody Attendance attendance) {

        return attendanceService.signOut(attendance);
    }
}
