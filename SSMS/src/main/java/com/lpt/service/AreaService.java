package com.lpt.service;

import com.lpt.pojo.Area;
import com.lpt.result.Result;

import java.util.List;

public interface AreaService {

    void delArea(Area area);

    void editArea(Area area);

    Result addArea(Area area);

    List<Area> getAllArea();
}
