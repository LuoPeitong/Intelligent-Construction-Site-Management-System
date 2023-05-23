package com.lpt.controller;

import com.lpt.pojo.Trajectory;
import com.lpt.result.Result;
import com.lpt.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrajectoryController {

    @Autowired
    private TrajectoryService trajectoryService;

    @RequestMapping(value = "/trajectory/getInfo")
    @ResponseBody
    public Result getInfo() {
        try{

            return trajectoryService.getInfo();
        } catch (Exception e){
            return new Result(400,null,"出错了");
        }
    }

    @RequestMapping(value = "/trajectory/setLocation")
    @ResponseBody
    public Result setLocation(@RequestBody Trajectory trajectory) {
        try{

            return trajectoryService.setLocation(trajectory);
        }
        catch (Exception e){
            System.out.println(e);
            return new Result(400,null,"出错了");
        }
    }

    @RequestMapping(value = "/trajectory/realTimeTrack")
    @ResponseBody
    public Result realTimeTrack() {
        try{
            return trajectoryService.realTimeTrack();
        }
        catch (Exception e){

            System.out.println(e);
            return new Result(400,null,"出错了");
        }
    }
}
