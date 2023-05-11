package com.lpt.service.Impl;

import com.lpt.dao.IStaffDao;
import com.lpt.dao.ITrajectoryDao;
import com.lpt.pojo.Attendance;
import com.lpt.pojo.Trajectory;
import com.lpt.result.Result;
import com.lpt.result.pojo.ResponsePerInfo;
import com.lpt.result.pojo.ResponseTrajectory;
import com.lpt.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("trajectoryService")
public class TrajectoryServiceImpl implements TrajectoryService {

    @Autowired
    private ITrajectoryDao iTrajectoryDao;

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public Result getInfo(){

        ResponsePerInfo responsePerInfo = new ResponsePerInfo();
        responsePerInfo.setPerInfo(iTrajectoryDao.getInfo());
        responsePerInfo.setStaffList(iStaffDao.findAll());
        return new Result(200,responsePerInfo,"请求成功");
    }

    @Override
    public void setLocation(Trajectory trajectory){

        //定义格式，不显示毫秒
        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取系统当前时间
        Timestamp now= new Timestamp(System.currentTimeMillis());
        trajectory.setMoment(datetime.format(now));
        iTrajectoryDao.setTrajectory(trajectory);
    }

    @Override
    public Result realTimeTrack(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        Timestamp now= new Timestamp(System.currentTimeMillis());//获取系统当前时间
        String moment = date.format(now);
        Attendance attendance = new Attendance();
        attendance.setMoment(moment);
        return new Result(200,iTrajectoryDao.realTimeTrack(attendance),"请求成功");
    }
}
