package com.lpt.controller;

import com.lpt.result.Result;
import com.lpt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuService menuService;

    @CrossOrigin
    @RequestMapping(value = "/menu/getMenu")
    @ResponseBody
    // 获取二级菜单
    public Result getMenu() {

        return new Result(200,menuService.getMenu(),"导航栏获取成功");
    }
}
