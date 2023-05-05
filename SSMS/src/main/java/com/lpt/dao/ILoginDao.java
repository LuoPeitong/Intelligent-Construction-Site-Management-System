package com.lpt.dao;

import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
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
}
