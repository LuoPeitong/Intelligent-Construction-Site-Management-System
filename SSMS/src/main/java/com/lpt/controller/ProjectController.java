package com.lpt.controller;

import com.lpt.result.Result;
import com.lpt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/project/getList")
    public Result getList(){

        return new Result(200,projectService.findAll(),"获取项目列表");
    }
}
