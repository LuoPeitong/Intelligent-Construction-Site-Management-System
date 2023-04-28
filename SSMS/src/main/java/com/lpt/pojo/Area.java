package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Area implements Serializable {

    private Integer id;
    private String region;
    private String classes;
    private String name;
    private String createBy;
    private String createTime;
}
