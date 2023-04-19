package com.lpt.result.pojo;

import com.lpt.pojo.Func;
import lombok.Data;

import java.util.List;

//左侧二级导航栏的结构
@Data
public class MenuList {
    private String title;
    private int id;
    private String img;
    private List<Func> subMenu;
}
