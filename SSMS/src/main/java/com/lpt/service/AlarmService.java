package com.lpt.service;

import com.lpt.pojo.Alarm;
import com.lpt.result.Result;

public interface AlarmService {

    Result getAll();

    Result editAlarm(Alarm alarm);

    Result addAlarm(Alarm alarm);
}
