package com.lpt.service.Impl;

import com.lpt.dao.IFuncDao;
import com.lpt.dao.IMenuDao;
import com.lpt.pojo.Func;
import com.lpt.pojo.Menu;
import com.lpt.result.pojo.MenuList;
import com.lpt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private IMenuDao iMenuDao;

    @Autowired
    private IFuncDao iFuncDao;

    @Override
    public List<MenuList> getMenu(){

        // 获取数据库数据
        List<Menu> menuList = iMenuDao.getMenu();
        List<Func> funcList = iFuncDao.getFunc();
        // 返回的结果
        List<MenuList> result = new ArrayList<>();
        // 一个对象，用于往result添加
        MenuList m = new MenuList();

        // 用于标记funcList循环的位置
        int flag=0;
        // 构成List<MenuList>
        for (Menu menu:menuList){
            m.setId(menu.getId());
            m.setImg(menu.getImg());
            m.setTitle(menu.getTitle());

            // 将二级菜单与一级菜单对应上
            List<Func> f = new ArrayList<>();
            for(int i=flag;i< funcList.size();i++){
                Func c = funcList.get(i);
                if(c.getMenuId()==menu.getId()){
                    f.add(c);
                    flag++;
                }
                else{
                    break;
                }
            }
            m.setSubMenu(f);
            result.add(m);
            m = new MenuList();
        }
        return result;
    }

    @Override
    public void add(Menu menu){
        iMenuDao.add(menu);
    }
}
