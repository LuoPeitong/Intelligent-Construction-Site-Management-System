package com.lpt.dao;

import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
import com.lpt.result.pojo.setAdmin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoginDao {

    @Select("select * from login")
    List<Login> findAll();

    @Select("select * from login where job_no=#{jobNo}")
    Login getByJobNo(Login login);

    @Insert("insert into login(job_no,pwd,permissions) value(#{jobNo},#{pwd},#{permissions})")
    void add(Login login);

    // 删除
    @Delete("delete from login where job_no=#{jobNo}")
    void delete(Login login);

    @Update("update login set pwd=#{pwd} where job_no=#{jobNo}")
    void editPwd(Login login);

    @Select("select a.job_no,a.name,d.name 'department_name',a.profession,l.permissions\n" +
            "from staff a join department d on a.department_id=d.id\n" +
            "join login l on l.job_no=a.job_no")
    List<setAdmin> setAdminQuery();

    @Update("update login set permissions=#{permissions} where job_no=#{jobNo}")
    void editPermissions(Login login);

    @Select("select count(*) num from login\n" +
            "where permissions='2'")
    int querySuperNum();
}
