package com.lpt.service.Impl;

import com.lpt.dao.IAlarmDao;
import com.lpt.pojo.Alarm;
import com.lpt.result.Result;
import com.lpt.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {
    @Autowired
    private IAlarmDao iAlarmDao;

    @Override
    public Result getAll() {

        return new Result(200,iAlarmDao.getAll(),"获取成功");
    }

    @Override
    public Result editAlarm(Alarm alarm) {

        if(alarm.getHandling()==null||"".equals(alarm.getHandling())){
            return new Result(201,null,"处理情况为空");
        }
        iAlarmDao.editAlarm(alarm);
        return new Result(200,iAlarmDao.getAll(),"处理成功");
    }

    @Override
    public Result addAlarm(Alarm alarm) {

        return new Result(200,iAlarmDao.getAll(),"成功");
    }
}
