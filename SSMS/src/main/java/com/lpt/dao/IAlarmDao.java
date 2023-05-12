package com.lpt.dao;

import com.lpt.pojo.Alarm;
import com.lpt.result.pojo.AlarmList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlarmDao {

    @Select("select a.id,a.job_no,a.moment,a.classes,a.handling,a.message,s.name,d.name departmentName,p.name projectName\n" +
            "from alarm a\n" +
            "join staff s on a.job_no=s.job_no\n" +
            "join department d on d.id = s.department_id\n" +
            "join project p on p.id=s.project_id")
    List<AlarmList> getAll();

    @Update("update alarm set handling = #{handling} where id=#{id}")
    void editAlarm(Alarm alarm);

    @Insert("insert into alarm (moment,classes,job_no,handling,message) values (#{moment},#{classes},#{jobNo},#{handling},#{message})")
    void addAlarm(Alarm alarm);
}
