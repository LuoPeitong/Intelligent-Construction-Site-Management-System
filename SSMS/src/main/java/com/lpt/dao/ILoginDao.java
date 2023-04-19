package com.lpt.dao;

import com.lpt.pojo.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoginDao {

    @Select("select * from login")
    public List<Login> findAll();

    @Select("select * from login where job_no=#{jobNo}")
    public Login getByJobNo(Login login);

    @Insert("insert into login(job_no,pwd,permissions) value(#{jobNo},#{pwd},#{permissions})")
    public void add(Login login);
}
