package com.lpt.result.pojo;

import com.lpt.pojo.Staff;
import lombok.Data;

import java.util.List;

@Data
public class ResponsePerInfo {

    private List<ResponseTrajectory> perInfo;
    private List<Staff> staffList;
}
