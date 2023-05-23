package com.lpt.controller;

import com.lpt.pojo.Alarm;
import com.lpt.result.Result;
import com.lpt.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @ResponseBody
    @RequestMapping(value = "/alarm/getAll")
    public Result getAll() {
        try{
            return alarmService.getAll();
        }
        catch (Exception e) {
            return new Result(400,null,"出错了");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/alarm/editAlarm")
    public Result editAlarm(@RequestBody Alarm alarm) {
        try{
            return alarmService.editAlarm(alarm);
        }
        catch (Exception e) {
            return new Result(400,null,"出错了");
        }
    }
}
