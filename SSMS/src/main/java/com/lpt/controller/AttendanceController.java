package com.lpt.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpt.pojo.Attendance;
import com.lpt.result.Result;
import com.lpt.result.pojo.RequestAttendance;
import com.lpt.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/getAttendData")
    public Result getAttendData(@RequestBody RequestAttendance requestAttendance) throws IOException {
        try{
            return attendanceService.getAttendData(requestAttendance);
        }
        catch (Exception e){

            return new Result(400,null,"出错了");
        }

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/export")
    public Result export(@RequestBody RequestAttendance requestAttendance) throws IOException {


        attendanceService.export(requestAttendance);
        return new Result(200,null,"员工考勤信息导出成功");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/attendance/findData")
    public Result findOriginal(@RequestBody Attendance attendance) {

        return attendanceService.findData(attendance);
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
