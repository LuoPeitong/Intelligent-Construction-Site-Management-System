package com.lpt.dao;

import com.lpt.pojo.Attendance;
import com.lpt.pojo.Staff;
import com.lpt.result.pojo.Report;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStaffDao {

    @Select("select * from staff where id=#{id}")
    Staff getById(Staff staff);

    @Select("select * from staff where job_no=#{jobNo}")
    Staff getByJobNo(String jobNo);

    // 根据项目分组
    @Select("select * from staff")
    List<Staff> findAll();

    // 根据部门分组
    @Select("select * from staff group by department_id")
    List<Staff> findAllGroupByDepartment();

    // 插入
    @Insert("insert into staff (job_no, name, department_id,profession,is_online,project_id,email) values(#{jobNo},#{name},#{departmentId},#{profession},#{isOnline},#{projectId},#{email})")
    void add(Staff staff);

    // 更新
    @Update("update staff set job_no=#{jobNo}, name=#{name}, department_id=#{departmentId}, profession=#{profession}, is_online=#{isOnline}, project_id=#{projectId}, email=#{email} where id=#{id}")
    void update(Staff staff);

    // 删除
    @Delete("delete from staff where id=#{id}")
    void delete(Staff staff);

    // 获取在线、离线人数 第一项数据是在线人数，第二项数据是离线人数
    @Select("select count(*) from staff group by is_online")
    List<Integer> getOnline();

    // 修改状态为在线
    @Update("update staff set is_online='在线' where id=#{id}")
    void SetIsOnline(Staff staff);

    // 修改状态为离线
    @Update("update staff set is_online='离线' where id=#{id}")
    void SetNotOnline(Staff staff);

    // 人员报表查询：获取当天数据
    @Select("select \n" +
            "s.job_no,s.name,s.profession,p.name 'project_name',d.name 'department_name',s.is_online,\n" +
            "COALESCE(a.enter_moment, '未出勤') as enter_moment, \n" +
            "COALESCE(a.leave_moment, '未出勤') as leave_moment\n" +
            "from\n" +
            "staff s join project p on s.project_id=p.id\n" +
            "join department d on s.department_id=d.id\n" +
            "left join attendance a on a.job_no=s.job_no and a.moment=#{moment}")
    List<Report> queryPerReport(Attendance attendance);
}
