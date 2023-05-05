package com.lpt.result.pojo;

import lombok.Data;

@Data
public class StaffList {

    private Integer id;
    private String jobNo;
    private String name;
    private Integer departmentId;
    private String departmentName;
    private String profession;
    private String isOnline;
    private Integer projectId;
    private String projectName;
    private String email;
}
