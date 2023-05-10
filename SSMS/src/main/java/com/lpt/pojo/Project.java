package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

@Data
public class Project implements Serializable {

    private Integer id;
    private String name;
    private String workArea;
    private String limitedArea;
    private String startTime;
    private String closingTime;
}
