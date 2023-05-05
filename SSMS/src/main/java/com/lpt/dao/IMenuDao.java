package com.lpt.dao;

import com.lpt.pojo.Menu;
import com.lpt.pojo.Staff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuDao {

    @Select("select m.id,m.title,m.img from menu as m join (select permissions from staff join login on staff.job_no=login.job_no and staff.job_no=#{jobNo}) as t on m.permissions<=t.permissions")
    public List<Menu> getMenu(Staff staff);

    @Insert("insert into menu(title,img) value (#{title}, #{img})")
    public void add(Menu menu);
}
