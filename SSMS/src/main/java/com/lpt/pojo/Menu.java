package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
    private int id;
    private String title;
    private String img;
}
