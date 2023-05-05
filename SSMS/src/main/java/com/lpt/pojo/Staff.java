package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Staff implements Serializable {

    private Integer id;
    private String jobNo;
    private String name;
    private Integer departmentId;
    private String profession;
    private String isOnline;
    private Integer projectId;
    private String email;
}
