package com.lpt.service.Impl;

import com.lpt.dao.IDepartmentDao;
import com.lpt.pojo.Department;
import com.lpt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private IDepartmentDao iDepartmentDao;

    @Override
    public List<Department> findAll(){

        return iDepartmentDao.findAll();
    }
}
