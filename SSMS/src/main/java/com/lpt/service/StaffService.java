package com.lpt.service;

import com.lpt.pojo.Staff;
import com.lpt.result.pojo.StaffList;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StaffService {

    public List<StaffList> findAll();

    public Staff getById(Staff staff);

    public Staff getByJobNo(String jobNo);

    public void editStaff(Staff staff);

    public void deleteStaff(Staff staff);

    public void SetIsOnline(Staff staff);

    public void SetNotOnline(Staff staff);
}
