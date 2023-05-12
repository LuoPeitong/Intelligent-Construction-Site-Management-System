package com.lpt.service.Impl;

import com.lpt.dao.*;
import com.lpt.pojo.*;
import com.lpt.result.Result;
import com.lpt.result.pojo.PointlnPolygon;
import com.lpt.result.pojo.ResponsePerInfo;
import com.lpt.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("trajectoryService")
public class TrajectoryServiceImpl implements TrajectoryService {

    @Autowired
    private ITrajectoryDao iTrajectoryDao;

    @Autowired
    private IStaffDao iStaffDao;

    @Autowired
    private IProjectDao iProjectDao;

    @Autowired
    private IAreaDao iAreaDao;

    @Autowired
    private IAlarmDao iAlarmDao;

    @Override
    public Result getInfo(){

        ResponsePerInfo responsePerInfo = new ResponsePerInfo();
        responsePerInfo.setPerInfo(iTrajectoryDao.getInfo());
        responsePerInfo.setStaffList(iStaffDao.findAll());
        return new Result(200,responsePerInfo,"请求成功");
    }

    @Override
    public Result setLocation(Trajectory trajectory){
        if(trajectory.getLocation()==null||"".equals(trajectory.getLocation())){

            return new Result(201,null,"定位不允许为空");
        }

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now= new Timestamp(System.currentTimeMillis());
        trajectory.setMoment(datetime.format(now));
        iTrajectoryDao.setTrajectory(trajectory);

        Project p = iProjectDao.queryByJobNo(trajectory);
        System.out.println("60 p = " +  p);
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String nowTime = time.format(now);
        // 字符串转为时间类型
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime startTime = LocalTime.parse(p.getStartTime(), formatter);
        LocalTime endTime = LocalTime.parse(p.getClosingTime(), formatter);
        LocalTime currentTime = LocalTime.parse(nowTime, formatter);
/**
 * 如果处于上班时间，则需要判断位置
 * 如果处于工作区域，则不需要继续判断
 * 如果不处于工作区域，则继续判断是否处于禁入区域（进入禁入区域优先级大于离开工作区域）
 */
        // 判断当前时间是否在上班时间和下班时间之间
        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            System.out.println("75在上班时间");
            String[] workArea = p.getWorkArea().split(",");
            String[] limitedArea = p.getLimitedArea().split(",");
            String[] location = trajectory.getLocation().split(",");
            List<Area> areaList = new ArrayList<>();
            for (int i = 0; i < workArea.length; i++) {

                Area area = new Area();
                area.setId(Integer.parseInt(workArea[i]));
                areaList.add(iAreaDao.queryById(area));
            }
            System.out.println("86 list= " +areaList);
            for (int i = 0; i < areaList.size(); i++) {
                if (PointlnPolygon.isPointInArea(Double.parseDouble(location[0]), Double.parseDouble(location[1]), areaList.get(i).getRegion())) {
                    System.out.println("在区域内");
                    return new Result(200,null,"定位上传成功");
                }
            }
            System.out.println("93 不在区域内");
            // 不在区域内
            Alarm alarm = new Alarm();
            alarm.setMoment(datetime.format(now));
            alarm.setJobNo(trajectory.getJobNo());
            alarm.setClasses("1");
            alarm.setMessage("离开工作区域");
            alarm.setHandling("0");

            System.out.println("102 alarm = " + alarm);
            // 判断是否在禁入区域
            areaList = new ArrayList<>();
            for (int i = 0; i < limitedArea.length; i++) {

                Area area = new Area();
                area.setId(Integer.parseInt(limitedArea[i]));
                areaList.add(iAreaDao.queryById(area));
            }
            System.out.println("111 lit = " + areaList);
            for (int i = 0; i < areaList.size(); i++) {
                if (PointlnPolygon.isPointInArea(Double.parseDouble(location[0]), Double.parseDouble(location[1]), areaList.get(i).getRegion())) {
                    // 在区域内
                    System.out.println("115 在区域内");
                    alarm.setMessage("离开了工作区域，且处于禁入区域："+areaList.get(i).getName());
                    alarm.setClasses("0");
                    iAlarmDao.addAlarm(alarm);
                    // 发消息通知管理端
                    return new Result(202, null, "你现在在禁入区域内");
                }
            }
            System.out.println("124 alarm= " + alarm);
            iAlarmDao.addAlarm(alarm);
            // 发消息通知管理端，此时单纯不在工作区域
            return new Result(202, null, "你现在不在工作区域内");
        }
        return new Result(200,null,"定位上传成功");
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
