package com.lpt.dao;

import com.lpt.pojo.Trajectory;
import com.lpt.result.pojo.ResponseTrajectory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrajectoryDao {

    @Select("select a.name,a.job_no,a.is_online,b.location,d.name departmentName ,p.name projectName from staff a, department d, project p, (select * from (select *,row_number() over ( PARTITION BY job_no ORDER BY moment DESC ) AS rn FROM trajectory) AS t where rn=1) as b where a.job_no =b.job_no and a.department_id =d.id  and a.project_id =p.id\n")
    public List<ResponseTrajectory> getInfo();

    @Insert("insert into trajectory (job_no,moment,location) values (#{jobNo},#{moment},#{location})")
    public void setTrajectory(Trajectory trajectory);
}
