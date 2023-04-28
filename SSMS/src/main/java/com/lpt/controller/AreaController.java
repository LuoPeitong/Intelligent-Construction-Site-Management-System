package com.lpt.controller;

import com.lpt.pojo.Area;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@CrossOrigin
@Controller
public class AreaController {

    @Autowired
    private AreaService areaService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/area/getAllArea")
    public Result getAllArea() {
        try{
            return new Result(200,areaService.getAllArea(),"请求成功");
        }
        catch (Exception e) {
            return new Result(400,null,"出错了");
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/area/addArea")
    public Result addArea(@RequestBody Area area) {
        try{
            return areaService.addArea(area);
        }
        catch (Exception e) {
            return new Result(400,null,"出错了,请联系开发人员");
        }
    }
}
