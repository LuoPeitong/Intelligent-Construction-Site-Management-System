package com.lpt.dao;

import com.lpt.pojo.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartmentDao {

    @Select("select * from department")
    List<Department> findAll();

    @Insert("insert into department (symbol,name,head,tel) values (#{symbol},#{name},#{head},#{tel})")
    void addDepartment (Department department);

    @Delete("delete from department where id = #{id}")
    void delDepartment(Department department);

    @Update("update department set symbol=#{symbol},name=#{name},head=#{head},tel=#{tel} where id=#{id}")
    void editDepartment(Department department);

    @Select("select count(*) as num\n" +
            "from department d join staff s on d.id=s.department_id\n" +
            "where d.id=#{id}")
    int queryByDepartment(Department department);

}
