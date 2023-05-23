package com.lpt.controller;

import com.lpt.pojo.Area;
import com.lpt.result.Result;
import com.lpt.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AreaController {

    @Autowired
    private AreaService areaService;

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

    @ResponseBody
    @RequestMapping(value = "/area/delArea")
    public Result delArea(@RequestBody Area area) {
        try{
            areaService.delArea(area);
            return new Result(200,null,"删除成功");
        }
        catch (Exception e) {
            return new Result(400,null,"出错了,请联系开发人员");
        }
    }
}
