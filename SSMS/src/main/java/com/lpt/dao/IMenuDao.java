package com.lpt.dao;

import com.lpt.pojo.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuDao {

    @Select("select * from menu")
    public List<Menu> getMenu();

    @Insert("insert into menu(title,img) value (#{title}, #{img})")
    public void add(Menu menu);
}
