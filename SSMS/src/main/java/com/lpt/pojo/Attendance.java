package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
public class Attendance implements Serializable {

    private Integer id;
    private String moment;
    private String jobNo;
    private String enterMoment;
    private String leaveMoment;
    private String name;
}
