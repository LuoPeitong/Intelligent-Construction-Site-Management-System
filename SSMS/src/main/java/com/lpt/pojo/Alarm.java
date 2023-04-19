package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Alarm implements Serializable {

    private Integer id;
    private String jobNo;
    private String handling;
    private String classes;
    private Timestamp moment;
}
