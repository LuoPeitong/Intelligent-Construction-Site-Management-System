package com.lpt.service;

import com.lpt.pojo.Attendance;
import com.lpt.result.Result;
import com.lpt.result.pojo.RequestAttendance;
import com.lpt.result.pojo.ResponseAttendance;
import com.lpt.result.pojo.ResponsePerStatistics;

import java.io.IOException;
import java.util.List;

public interface AttendanceService {

    public Result getAttendData(RequestAttendance requestAttendance);

    public void export(RequestAttendance requestAttendance) throws IOException;

    public Result findData(Attendance attendance);

    public Result signIn(Attendance attendance);

    public Result signOut(Attendance attendance);
}
