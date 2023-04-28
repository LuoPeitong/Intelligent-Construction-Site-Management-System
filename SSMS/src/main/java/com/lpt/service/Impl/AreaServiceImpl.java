package com.lpt.service.Impl;

import com.lpt.dao.IAreaDao;
import com.lpt.pojo.Area;
import com.lpt.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Autowired
    private IAreaDao iAreaDao;

    @Override
    public void delArea(Area area){

        iAreaDao.delArea(area);
    }

    @Override
    public void editArea(Area area){

        iAreaDao.editArea(area);
    }

    @Override
    public void addArea(Area area){

        iAreaDao.addArea(area);
    }

    @Override
    public List<Area> getAllArea(){

        return iAreaDao.getAllArea();
    }
}
