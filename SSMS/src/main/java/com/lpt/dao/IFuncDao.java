package com.lpt.dao;

import com.lpt.pojo.Func;
import com.lpt.pojo.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFuncDao {
// mybatis
    @Select("select * from func order by menu_id")
    public List<Func> getFunc();

    @Insert("insert into func(title,menu_id,content) value (#{title}, #{menuId}, #{content})")
    public void add(Func func);
}
