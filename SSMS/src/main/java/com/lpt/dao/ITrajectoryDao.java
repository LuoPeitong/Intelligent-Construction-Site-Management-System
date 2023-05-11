package com.lpt.dao;

import com.lpt.pojo.Attendance;
import com.lpt.pojo.Trajectory;
import com.lpt.result.pojo.PerRealTimeTrack;
import com.lpt.result.pojo.ResponseTrajectory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITrajectoryDao {

    @Select("select a.name,a.job_no,a.is_online,b.location,d.name departmentName ,p.name projectName from staff a, department d, project p, (select * from (select *,row_number() over ( PARTITION BY job_no ORDER BY moment DESC ) AS rn FROM trajectory) AS t where rn=1) as b where a.job_no =b.job_no and a.department_id =d.id  and a.project_id =p.id\n")
    List<ResponseTrajectory> getInfo();

    @Insert("insert into trajectory (job_no,moment,location) values (#{jobNo},#{moment},#{location})")
    void setTrajectory(Trajectory trajectory);

    @Select("SELECT \n" +
            "    s.job_no, \n" +
            "    s.name, \n" +
            "    s.profession, \n" +
            "    d.name departmentName, \n" +
            "    p.name projectName, \n" +
            "    GROUP_CONCAT(t.location ORDER BY t.moment ASC SEPARATOR ';') AS locations\n" +
            "FROM \n" +
            "    staff s \n" +
            "    JOIN department d ON s.department_id=d.id\n" +
            "    JOIN project p ON p.id=s.project_id\n" +
            "    JOIN trajectory t ON t.job_no=s.job_no\n" +
            "WHERE \n" +
            "    t.moment LIKE '${moment}%'\n" +
            "GROUP BY \n" +
            "    s.job_no, \n" +
            "    s.name, \n" +
            "    s.profession, \n" +
            "    d.name, \n" +
            "    p.name;")
    List<PerRealTimeTrack> realTimeTrack(Attendance attendance);
}
