package com.lpt.controller;

import com.lpt.pojo.Attendance;
import com.lpt.pojo.Trajectory;
import com.lpt.result.Result;
import com.lpt.result.pojo.RequestAttendance;
import com.lpt.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

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

    @ResponseBody
    @RequestMapping(value = "/attendance/export")
    public Result export(@RequestBody RequestAttendance requestAttendance) throws IOException {


        attendanceService.export(requestAttendance);
        return new Result(200,null,"员工考勤信息导出成功");
    }

    @ResponseBody
    @RequestMapping(value = "/attendance/findData")
    public Result findOriginal(@RequestBody Attendance attendance) {

        return attendanceService.findData(attendance);
    }

    @ResponseBody
    @RequestMapping(value = "/attendance/signIn")
    public Result signIn(@RequestBody Trajectory trajectory) {
        try{

            return attendanceService.signIn(trajectory);
        }
        catch (Exception e){
            return new Result(400,null,"出错了");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/attendance/signOut")
    public Result signOut(@RequestBody Trajectory trajectory) {
        try{
            return attendanceService.signOut(trajectory);
        }
        catch (Exception e){
            return new Result(400,null,"出错了");
        }
    }
}
