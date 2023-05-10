package com.lpt.service.Impl;

import com.lpt.dao.IProjectDao;
import com.lpt.pojo.Project;
import com.lpt.result.Result;
import com.lpt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private IProjectDao iProjectDao;

    @Override
    public Result findAll(){

        return new Result(200,iProjectDao.findAll(),"请求成功");
    }

    @Override
    public Result editProject(Project project){
        if(project.getName()==null||"".equals(project.getName())){
            return new Result(201,null,"项目名称不能为空");
        }
        if(project.getWorkArea()==null||"".equals(project.getWorkArea())){
            return new Result(201,null,"工作区域不能为空");
        }
        if(project.getStartTime()==null||"".equals(project.getStartTime())){
            return new Result(201,null,"上班时间不能为空");
        }
        if(project.getClosingTime()==null||"".equals(project.getClosingTime())){
            return new Result(201,null,"下班时间不能为空");
        }
        if(project.getId()!=null) {
            iProjectDao.editProject(project);
        }
        else{
            iProjectDao.addProject(project);
        }
        return new Result(200,iProjectDao.findAll(),"编辑项目成功");
    }

    @Override
    public Result delProject(Project project){
        if(iProjectDao.queryByProject(project)!=0){
            return new Result(202,null,"不满足删除条件");
        }
        iProjectDao.delProject(project);
        return new Result(200,iProjectDao.findAll(),"删除项目成功");
    }
}
