package com.lpt.dao;

import com.lpt.pojo.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectDao {

    @Select("select * from project")
    public List<Project> findAll();
}
