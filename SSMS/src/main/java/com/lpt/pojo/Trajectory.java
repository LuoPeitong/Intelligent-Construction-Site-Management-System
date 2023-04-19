package com.lpt.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Trajectory implements Serializable {

    private Integer id;
    private String jobNo;
    private String moment;
    private String location;
}
