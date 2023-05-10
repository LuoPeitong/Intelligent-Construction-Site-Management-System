package com.lpt.service;

import com.lpt.pojo.Project;
import com.lpt.result.Result;

import java.util.List;

public interface ProjectService {

    Result findAll();

    Result editProject(Project project);

    Result delProject(Project project);
}
