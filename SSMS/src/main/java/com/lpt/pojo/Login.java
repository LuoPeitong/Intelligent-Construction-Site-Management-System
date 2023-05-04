package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Login implements Serializable {

    private Integer id;
    private String jobNo;
    private String pwd;
    private char permissions;
}
