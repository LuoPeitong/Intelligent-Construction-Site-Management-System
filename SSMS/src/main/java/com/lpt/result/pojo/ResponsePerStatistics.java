package com.lpt.result.pojo;

import lombok.Data;

@Data
public class ResponsePerStatistics {

    // 总数
    private Integer total;
    private Integer isOnline;
    private Integer notOnline;
    private Integer late;
    private ResponseAttendance leaveEarly; //最早离场
    private ResponseAttendance lateEnter;  //最晚入场
    private ResponseAttendance original;   //最早入场
    private ResponseAttendance last;       //最晚离场
}
