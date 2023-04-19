package com.lpt.service.Impl;

import com.lpt.dao.ITrajectoryDao;
import com.lpt.pojo.Trajectory;
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

    @Override
    public List<ResponseTrajectory> getInfo(){

        return iTrajectoryDao.getInfo();
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
}
