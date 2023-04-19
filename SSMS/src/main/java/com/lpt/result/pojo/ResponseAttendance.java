package com.lpt.result.pojo;

import lombok.Data;

@Data
public class ResponseAttendance {

    private String jobNo;
    private String name;
    private String departmentName;
    private String projectName;
    private String enterMoment;
    private String leaveMoment;
}
