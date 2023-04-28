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
    public Result addArea(@RequestBody Area area, HttpServletRequest request) {
        try{
            if(area.getClasses()==null||"".equals(area.getClasses())){
                return new Result(401,null,"类型为空，请求失败");
            }
            if(area.getRegion()==null||"".equals(area.getRegion())){
                return new Result(401,null,"范围不存在，请求失败");
            }
            if(area.getName()==null||"".equals(area.getName())){
                return new Result(401,null,"区域名称为空，请求失败");
            }

            if (area.getCreateBy()==null||"".equals(area.getCreateBy())) {
                return new Result(401,null,"未登录，请登录后再试");
            }

            //定义格式，不显示毫秒
            SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取系统当前时间
            Timestamp now= new Timestamp(System.currentTimeMillis());
            area.setCreateTime(datetime.format(now));
            System.out.println("获取session之后"+area);
            areaService.addArea(area);

            return new Result(200,null,"新增区域成功");
        }
        catch (Exception e) {
            return new Result(400,null,"出错了");
        }
    }
}
