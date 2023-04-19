package com.lpt.service;

import com.lpt.pojo.Attendance;
import com.lpt.result.Result;
import com.lpt.result.pojo.ResponseAttendance;
import com.lpt.result.pojo.ResponsePerStatistics;

import java.io.IOException;
import java.util.List;

public interface AttendanceService {

    public List<Attendance> findAll();

    public void export() throws IOException;

    public ResponsePerStatistics findData(Attendance attendance);

    public Result signIn(Attendance attendance);

    public Result signOut(Attendance attendance);
}
