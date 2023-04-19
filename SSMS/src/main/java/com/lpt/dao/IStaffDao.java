package com.lpt.dao;

import com.lpt.pojo.Staff;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStaffDao {

    @Select("select * from staff where id=#{id}")
    public Staff getById(Staff staff);

    @Select("select * from staff where job_no=#{jobNo}")
    public Staff getByJobNo(String jobNo);

    // 根据项目分组
    @Select("select * from staff")
    public List<Staff> findAll();

    // 根据部门分组
    @Select("select * from staff group by department_id")
    public List<Staff> findAllGroupByDepartment();

    // 插入
    @Insert("insert into staff (job_no, name, department_id,profession,is_online,project_id) values(#{jobNo},#{name},#{departmentId},#{profession},#{isOnline},#{projectId})")
    public void add(Staff staff);

    // 更新
    @Update("update staff set job_no=#{jobNo}, name=#{name}, department_id=#{departmentId}, profession=#{profession}, is_online=#{isOnline}, project_id=#{projectId} where id=#{id}")
    public void update(Staff staff);

    // 删除
    @Delete("delete from staff where id=#{id}")
    public void delete(Staff staff);

    // 获取在线、离线人数 第一项数据是在线人数，第二项数据是离线人数
    @Select("select count(*) from staff group by is_online")
    public List<Integer> getOnline();

    // 修改状态为在线
    @Update("update staff set is_online='在线' where id=#{id}")
    public void SetIsOnline(Staff staff);

    // 修改状态为离线
    @Update("update staff set is_online='离线' where id=#{id}")
    public void SetNotOnline(Staff staff);
}
