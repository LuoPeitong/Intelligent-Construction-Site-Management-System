package com.lpt.dao;

import com.lpt.pojo.Attendance;
import com.lpt.pojo.Department;
import com.lpt.pojo.Project;
import com.lpt.pojo.Trajectory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectDao {

    @Select("select * from project")
    List<Project> findAll();

    // 根据job_no查找工作区域
    @Select("select work_area from project right join staff on staff.project_id=project.id where staff.job_no=#{jobNo}")
    Project queryByJobNo(Trajectory trajectory);

    @Insert("insert into project (name,work_area,limited_area,start_time,closing_time) values (#{name},#{workArea},#{limitedArea},#{startTime},#{closingTime})")
    void addProject(Project project);

    @Delete("delete from project where id=#{id}")
    void delProject(Project project);

    @Update("update project set name = #{name},work_area = #{workArea},limited_area = #{limitedArea},start_time = #{startTime},closing_time = #{closingTime} where id=#{id}")
    void editProject(Project project);

    @Select("select count(*) as num\n" +
            "from project p join staff s on p.id=s.project_id\n" +
            "where p.id=#{id}")
    int queryByProject(Project project);
}
