package com.lpt.service.Impl;

import com.lpt.dao.IAreaDao;
import com.lpt.pojo.Area;
import com.lpt.result.Result;
import com.lpt.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    public Result addArea(Area area){

        if(area.getClasses()==null||"".equals(area.getClasses())){
            return new Result(201,null,"类型为空");
        }
        if(area.getRegion()==null||"".equals(area.getRegion())){
            return new Result(201,null,"范围为空");
        }
        if(area.getName()==null||"".equals(area.getName())){
            return new Result(201,null,"区域名称为空");
        }

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp now= new Timestamp(System.currentTimeMillis());
        area.setCreateTime(datetime.format(now));

        if(area.getId()==null){
            if (area.getCreateBy()==null||"".equals(area.getCreateBy())) {
                return new Result(202,null,"未登录，请先登录");
            }
            iAreaDao.addArea(area);
        }
        else{
            iAreaDao.editArea(area);
        }

        return new Result(200,iAreaDao.getAllArea(),"操作成功");
    }

    @Override
    public List<Area> getAllArea(){

        return iAreaDao.getAllArea();
    }
}
