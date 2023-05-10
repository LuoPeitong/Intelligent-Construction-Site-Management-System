package com.lpt.dao;

import com.lpt.pojo.Area;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAreaDao {

    @Select("select * from area")
    List<Area> getAllArea();

    @Select("select * from area where id=#{id}")
    Area queryById(Area area);

    @Select("select ")

    @Insert("insert into area (region,classes,name,create_by,create_time) values(#{region}, #{classes}, #{name}, #{createBy}, #{createTime})")
    void addArea(Area area);

    @Update("update area set region=#{region}, classes=#{classes}, name=#{name} where id=#{id}")
    void editArea(Area area);

    @Delete("delete from area where id = #{id}")
    void delArea(Area area);
}
