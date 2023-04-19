package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Func implements Serializable {
    private int id;
    private String title;
    private int menuId;
    private String content;
}
