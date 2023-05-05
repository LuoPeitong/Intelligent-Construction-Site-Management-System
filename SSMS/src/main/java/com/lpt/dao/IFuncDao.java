package com.lpt.dao;

import com.lpt.pojo.Func;
import com.lpt.pojo.Menu;
import com.lpt.pojo.Staff;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFuncDao {
// mybatis
    @Select("select f.id,f.menu_id,f.title,f.content from func as f join (select permissions from staff join login on staff.job_no=login.job_no and staff.job_no=#{jobNo}) as t on f.permissions<=t.permissions order by menu_id ")
    public List<Func> getFunc(Staff staff);

    @Insert("insert into func(title,menu_id,content) value (#{title}, #{menuId}, #{content})")
    public void add(Func func);
}
