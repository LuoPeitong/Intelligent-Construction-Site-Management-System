package com.lpt.service;

import com.lpt.pojo.Attendance;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.result.pojo.Report;
import com.lpt.result.pojo.StaffList;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StaffService {

    List<StaffList> findAll();

    Staff getById(Staff staff);

    Staff getByJobNo(String jobNo);

    Result editStaff(Staff staff);

    Result deleteStaff(Staff staff);

    void SetIsOnline(Staff staff);

    void SetNotOnline(Staff staff);

    Result queryPerReport();
}
