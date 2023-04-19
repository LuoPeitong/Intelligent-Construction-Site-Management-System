package com.lpt.service;

import com.lpt.pojo.Trajectory;
import com.lpt.result.pojo.ResponseTrajectory;

import java.util.List;

public interface TrajectoryService {

    public List<ResponseTrajectory> getInfo();

    public void setLocation(Trajectory trajectory);
}
