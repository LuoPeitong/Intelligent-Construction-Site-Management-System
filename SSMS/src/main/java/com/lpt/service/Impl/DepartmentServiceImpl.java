package com.lpt.service.Impl;

import com.lpt.dao.IDepartmentDao;
import com.lpt.pojo.Department;
import com.lpt.result.Result;
import com.lpt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private IDepartmentDao iDepartmentDao;

    @Override
    public Result findAll(){

        return new Result(200,iDepartmentDao.findAll(),"数据请求成功");
    }

    @Override
    public Result editDepartment(Department department) {
        if(department.getName()==null||"".equals(department.getName())){
            return new Result(201,null,"部门名称不能为空");
        }
        if(department.getSymbol()==null||"".equals(department.getSymbol())){
            return new Result(201,null,"部门代号不能为空");
        }
        if(department.getHead()==null||"".equals(department.getHead())){
            return new Result(201,null,"部门负责人不能为空");
        }
        if(department.getTel()==null||"".equals(department.getTel())){
            return new Result(201,null,"部门联系方式不能为空");
        }
        if(department.getId()!=null) {
            iDepartmentDao.editDepartment(department);
        }
        else{
            iDepartmentDao.addDepartment(department);
        }
        return new Result(200,iDepartmentDao.findAll(),"编辑部门成功");
    }

    @Override
    public Result delDepartment(Department department) {
        if(iDepartmentDao.queryByDepartment(department)!=0){
            return new Result(202,null,"不满足删除条件");
        }
        iDepartmentDao.delDepartment(department);
        return new Result(200,iDepartmentDao.findAll(),"删除部门成功");
    }
}
