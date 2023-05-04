package com.lpt.service.Impl;

import com.lpt.dao.IAttendanceDao;
import com.lpt.dao.IStaffDao;
import com.lpt.pojo.Attendance;
import com.lpt.result.Result;
import com.lpt.result.pojo.RequestAttendance;
import com.lpt.result.pojo.ResponseAttendance;
import com.lpt.result.pojo.ResponsePerStatistics;
import com.lpt.service.AttendanceService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private IAttendanceDao iAttendanceDao;

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public Result getAttendData(RequestAttendance requestAttendance){

        List<Attendance> list = iAttendanceDao.getAttendData(requestAttendance);
        if(list.size()>=1){

            return new Result(200,list,"请求成功");
        }
        else {
            return new Result(201,null,"没有数据");
        }
    }

    @Override
    public void export(RequestAttendance requestAttendance) throws IOException {

        List<Attendance> attendances = iAttendanceDao.getAttendData(requestAttendance);

        //创建工作簿 类似于创建Excel文件
        XSSFWorkbook workbook=new XSSFWorkbook();
        //创建 sheetname页名
        XSSFSheet sheet = workbook.createSheet("员工考勤情况");
        //创建一行,下标从0开始
        XSSFRow row = sheet.createRow(0);
        //创建这行中的列,下标从0开始 （表头）
        XSSFCell cell = row.createCell(0);
        //创建这行中的列,并给该列直接赋值
        cell.setCellValue("日期");
        row.createCell(1).setCellValue("工号");
        row.createCell(2).setCellValue("姓名");
        row.createCell(3).setCellValue("进场时间");
        row.createCell(4).setCellValue("离场时间");

        // 行号
        int count=1;
        for(Attendance attendance:attendances){
            row = sheet.createRow(count++);
            row.createCell(0).setCellValue(attendance.getMoment());
            row.createCell(1).setCellValue(attendance.getJobNo());
            row.createCell(2).setCellValue(attendance.getName());
            row.createCell(3).setCellValue(attendance.getEnterMoment());
            row.createCell(4).setCellValue(attendance.getLeaveMoment());
        }
        String projectPath = System.getProperty("user.dir");
        String relativePath = "员工考勤.xlsx";

        //设定 路径
        File file = new File(projectPath + "/" + relativePath);
        FileOutputStream stream = new FileOutputStream(file);
        // 需要抛异常
        workbook.write(stream);
        //关流
        stream.close();
    }

    @Override
    public Result findData(Attendance attendance){

        ResponsePerStatistics responsePerStatistics=new ResponsePerStatistics();
        // 获取在线、离线人数 第一项数据是在线人数，第二项数据是离线人数
        List<Integer> integers = iStaffDao.getOnline();
        responsePerStatistics.setTotal(integers.get(0)+integers.get(1));
        responsePerStatistics.setIsOnline(integers.get(0));
        responsePerStatistics.setNotOnline(integers.get(1));
        responsePerStatistics.setLate(iAttendanceDao.getLate(attendance));
        responsePerStatistics.setLateEnter(iAttendanceDao.findLateEnter(attendance));
        responsePerStatistics.setLeaveEarly(iAttendanceDao.findLeaveEarly(attendance));
        responsePerStatistics.setLast(iAttendanceDao.findLast(attendance));
        responsePerStatistics.setOriginal(iAttendanceDao.findOriginal(attendance));

        if(responsePerStatistics.getLeaveEarly()==null){
            return new Result(201,null,"当日无最早离场信息");
        }
        if(responsePerStatistics.getLateEnter()==null){
            return new Result(201,null,"当日无最晚进厂信息");
        }
        if(responsePerStatistics.getLast()==null){
            return new Result(201,null,"当日无最晚离场人员信息");
        }
        if(responsePerStatistics.getOriginal()==null){
            return new Result(201,null,"当日无最早进场人员信息");
        }
        return new Result(200,responsePerStatistics,"请求成功");
    }

    @Override
    public Result signIn(Attendance attendance){

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//定义格式，不显示毫秒
        Timestamp now= new Timestamp(System.currentTimeMillis());//获取系统当前时间
        String moment = date.format(now);
        String enterMoment = time.format(now);

        attendance.setMoment(moment);

        if(iAttendanceDao.findByJobNoAndMoment(attendance) == null){
            attendance.setEnterMoment(enterMoment);
            iAttendanceDao.signIn(attendance);
        }
        else{
            return new Result(202,null,"已经打过卡啦");
        }
        return new Result(200,null,"签到成功");
    }

    @Override
    public Result signOut(Attendance attendance){

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//定义格式，不显示毫秒
        Timestamp now= new Timestamp(System.currentTimeMillis());//获取系统当前时间
        String moment = date.format(now);
        String leaveMoment = time.format(now);

        attendance.setMoment(moment);
        if(iAttendanceDao.findByJobNoAndMoment(attendance) == null){

            return new Result(204,null,"打卡失败！因为你今天没有签到");
        }
        else if(iAttendanceDao.findByJobNoAndMoment(attendance).getLeaveMoment()==null||"".equals(iAttendanceDao.findByJobNoAndMoment(attendance).getLeaveMoment()))
        {
            attendance.setLeaveMoment(leaveMoment);
            iAttendanceDao.signOut(attendance);
            return new Result(200,null,"签退成功");
        }
        else{

            return new Result(202,null,"已经打过卡啦");
        }
    }
}
