package com.lpt.service;

import com.lpt.pojo.Area;

import java.util.List;

public interface AreaService {

    void delArea(Area area);

    void editArea(Area area);

    void addArea(Area area);

    List<Area> getAllArea();
}
