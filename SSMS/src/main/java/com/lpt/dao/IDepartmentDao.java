package com.lpt.dao;

import com.lpt.pojo.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartmentDao {

    @Select("select * from department")
    public List<Department> findAll();
}
