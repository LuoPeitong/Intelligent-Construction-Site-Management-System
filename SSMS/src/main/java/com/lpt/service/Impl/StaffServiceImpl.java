package com.lpt.service.Impl;

import com.lpt.dao.IDepartmentDao;
import com.lpt.dao.ILoginDao;
import com.lpt.dao.IProjectDao;
import com.lpt.dao.IStaffDao;
import com.lpt.pojo.Department;
import com.lpt.pojo.Login;
import com.lpt.pojo.Project;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.result.pojo.StaffList;
import com.lpt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private IStaffDao iStaffDao;

    @Autowired
    private IDepartmentDao iDepartmentDao;

    @Autowired
    private IProjectDao iProjectDao;

    @Autowired
    private ILoginDao iLoginDao;

    @Override
    public List<StaffList> findAll(){

        List<Staff> staffs = iStaffDao.findAll();
        List<Project> projects = iProjectDao.findAll();
        List<Department> departments = iDepartmentDao.findAll();

        // response的人员列表
        List<StaffList> responseStaffList = new ArrayList<>();
        StaffList s = new StaffList();
        for(Staff staff:staffs){
            s.setId(staff.getId());
            s.setName(staff.getName());
            s.setIsOnline(staff.getIsOnline());
            s.setDepartmentId(staff.getDepartmentId());
            s.setProfession(staff.getProfession());
            s.setProjectId(staff.getProjectId());
            s.setJobNo(staff.getJobNo());
            s.setDepartmentName(departments.get(staff.getDepartmentId()-1).getName());
            s.setProjectName(projects.get(staff.getProjectId()-1).getName());
            responseStaffList.add(s);
            s = new StaffList();
        }
        return responseStaffList;
    }

    @Override
    public Staff getById(Staff staff){

        return iStaffDao.getById(staff);
    }

    @Override
    public Staff getByJobNo(String jobNo){

        return iStaffDao.getByJobNo(jobNo);
    }

    @Override
    public Result editStaff(Staff staff){

        if(staff.getId()!=null) {
            iStaffDao.update(staff);
        }
        else{

            staff.setIsOnline("离线");
            iStaffDao.add(staff);

            Login login = new Login();
            login.setJobNo(staff.getJobNo());
            login.setPermissions('0');
            login.setPwd("123");
            System.out.println(login);
            iLoginDao.add(login);
        }
        return new Result(200,iStaffDao.findAll(),"编辑成功");
    }

    @Override
    public Result deleteStaff(Staff staff){

        Staff s = iStaffDao.getById(staff);
        Login login = new Login();
        login.setJobNo(s.getJobNo());
        iLoginDao.delete(login);
        iStaffDao.delete(staff);

        return new Result(200,iStaffDao.findAll(),"删除成功");
    }

    @Override
    public void SetIsOnline(Staff staff) {

        iStaffDao.SetIsOnline(staff);
    }

    @Override
    public void SetNotOnline(Staff staff){

        iStaffDao.SetNotOnline(staff);
    }
}
