package com.lpt.controller;

import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
import com.lpt.pojo.Trajectory;
import com.lpt.result.Result;
import com.lpt.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class TrajectoryController {

    @Autowired
    private TrajectoryService trajectoryService;

    @CrossOrigin
    @RequestMapping(value = "/trajectory/getInfo")
    @ResponseBody
    public Result getInfo() {

        return new Result(200,trajectoryService.getInfo(),"获取人员信息成功");
    }

    @CrossOrigin
    @RequestMapping(value = "/trajectory/setLocation")
    @ResponseBody
    public Result setLocation(@RequestBody Trajectory trajectory) {

        trajectoryService.setLocation(trajectory);
        return new Result(200,null,"定位上传成功");
    }
}
