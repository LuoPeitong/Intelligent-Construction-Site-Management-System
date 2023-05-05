package com.lpt.service;

import com.lpt.pojo.Menu;
import com.lpt.pojo.Staff;
import com.lpt.result.pojo.MenuList;

import java.util.List;

public interface MenuService {

    // 获取二级菜单
    public List<MenuList> getMenu(Staff staff);

    // 添加菜单
    public void add(Menu menu);
}
