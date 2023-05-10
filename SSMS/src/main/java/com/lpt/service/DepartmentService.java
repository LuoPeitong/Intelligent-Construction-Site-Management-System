package com.lpt.service;

import com.lpt.pojo.Department;
import com.lpt.result.Result;

import java.util.List;

public interface DepartmentService {

    Result findAll();

    Result editDepartment(Department department);

    Result delDepartment(Department department);
}
