package com.lpt.controller;

import com.lpt.pojo.Project;
import com.lpt.result.Result;
import com.lpt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
        try{

            return projectService.findAll();
        }
        catch (Exception e){
            return new Result(400,null,"出错了！");
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/project/editProject")
    public Result editProject(@RequestBody Project project){
        try{

            return projectService.editProject(project);
        }
        catch (Exception e){
            return new Result(400,null,"出错了！");
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/project/delProject")
    public Result delProject(@RequestBody Project project){
        try{

            return projectService.delProject(project);
        }
        catch (Exception e){
            return new Result(400,null,"出错了！");
        }
    }
}
