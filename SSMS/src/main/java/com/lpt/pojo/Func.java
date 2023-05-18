package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Func implements Serializable {
    private String id;
    private String title;
    private String menuId;
    private String content;
}
