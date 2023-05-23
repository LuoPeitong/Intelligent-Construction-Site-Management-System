package com.lpt.controller;

import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menu/getMenu")
    @ResponseBody
    // 获取二级菜单
    public Result getMenu(@RequestBody Staff staff) {
        return new Result(200,menuService.getMenu(staff),"导航栏获取成功");
    }
}
