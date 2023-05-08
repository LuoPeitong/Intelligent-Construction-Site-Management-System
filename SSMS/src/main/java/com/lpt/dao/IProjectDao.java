package com.lpt.dao;

import com.lpt.pojo.Attendance;
import com.lpt.pojo.Project;
import com.lpt.pojo.Trajectory;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectDao {

    @Select("select * from project")
    List<Project> findAll();

    // 根据job_no查找工作区域
    @Select("select work_area from project right join staff on staff.project_id=project.id where staff.job_no=#{jobNo}")
    Project queryByJobNo(Trajectory trajectory);
}
