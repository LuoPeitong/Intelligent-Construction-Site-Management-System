package com.lpt.result.pojo;

import lombok.Data;

@Data
public class AlarmList {
    private Integer id;
    private String jobNo;
    private String handling;
    private String classes;
    private String moment;
    private String name;
    private String message;
    private String departmentName;
    private String projectName;
}
