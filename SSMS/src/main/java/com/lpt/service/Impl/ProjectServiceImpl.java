package com.lpt.service.Impl;

import com.lpt.dao.IProjectDao;
import com.lpt.pojo.Project;
import com.lpt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private IProjectDao iProjectDao;

    @Override
    public List<Project> findAll(){

        return iProjectDao.findAll();
    }
}
