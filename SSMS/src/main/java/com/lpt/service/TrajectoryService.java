package com.lpt.service;

import com.lpt.pojo.Trajectory;
import com.lpt.result.Result;
import com.lpt.result.pojo.ResponseTrajectory;

import java.util.List;

public interface TrajectoryService {

    Result getInfo();

    void setLocation(Trajectory trajectory);

    Result realTimeTrack();
}
