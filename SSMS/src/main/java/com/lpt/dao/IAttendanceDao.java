package com.lpt.dao;

import com.lpt.pojo.Attendance;
import com.lpt.result.pojo.ResponseAttendance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceDao {

    @Select("select a.moment,a.job_no,b.name,a.enter_moment,a.leave_moment from attendance as a ,staff as b where a.job_no =b.job_no order by a.moment")
    public List<Attendance> findAll();

    // 所选日期 最早入场
    @Select("select a.enter_moment,a.leave_moment,b.job_no,b.name,c.name departmentName,p.name projectName from attendance as a,staff as b, department as c, project as p where a.moment=#{moment} and a.job_no =b.job_no  and b.department_id =c.id  and b.project_id =p.id  order by enter_moment  limit 1")
    public ResponseAttendance findOriginal(Attendance attendance);

    // 所选日期 最晚离场
    @Select("select a.enter_moment,a.leave_moment,b.job_no,b.name,c.name departmentName,p.name projectName from attendance as a,staff as b, department as c, project as p where a.moment=#{moment} and a.job_no =b.job_no  and b.department_id =c.id  and b.project_id =p.id  order by leave_moment desc limit 1")
    public ResponseAttendance findLast(Attendance attendance);

    // 所选日期 超时人数
    @Select("select count(*) from attendance where moment =#{moment} and enter_moment >'10:00:00'")
    public Integer getLate(Attendance attendance);

    // 所选日期 最晚入场
    @Select("select a.enter_moment,a.leave_moment,b.job_no,b.name,c.name departmentName,p.name projectName from attendance as a,staff as b, department as c, project as p where a.moment=#{moment} and a.job_no =b.job_no  and b.department_id =c.id  and b.project_id =p.id  order by enter_moment desc limit 1")
    public ResponseAttendance findLateEnter(Attendance attendance);

    // 所选日期 最早离场
    @Select("select a.enter_moment,a.leave_moment,b.job_no,b.name,c.name departmentName,p.name projectName from attendance as a,staff as b, department as c, project as p where a.moment=#{moment} and a.job_no =b.job_no  and b.department_id =c.id  and b.project_id =p.id  order by leave_moment limit 1")
    public ResponseAttendance findLeaveEarly(Attendance attendance);

    // 签到
    @Insert("insert into attendance (moment,job_no,enter_moment) values (#{moment},#{jobNo},#{enterMoment})")
    public void signIn(Attendance attendance);

    // 签退
    @Insert("update attendance set leave_moment=#{leaveMoment} where moment=#{moment} and job_no=#{jobNo}")
    public void signOut(Attendance attendance);

    // 用于判断是否已经打过卡
    @Select("select * from attendance where job_no=#{jobNo} and moment=#{moment}")
    public Attendance findByJobNoAndMoment(Attendance attendance);
}
