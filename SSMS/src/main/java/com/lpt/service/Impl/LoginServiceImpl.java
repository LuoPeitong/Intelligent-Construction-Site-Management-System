package com.lpt.service.Impl;

import com.lpt.dao.ILoginDao;
import com.lpt.dao.IStaffDao;
import com.lpt.pojo.Login;
import com.lpt.pojo.Staff;
import com.lpt.result.Result;
import com.lpt.result.pojo.Mail;
import com.lpt.result.pojo.RequestStaff;
import com.lpt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Random;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ILoginDao iLoginDao;

    @Autowired
    private IStaffDao iStaffDao;

    @Override
    public List<Login> findAll(){

        return iLoginDao.findAll();
    }

    @Override
    public Result getCode(Staff staff) throws MessagingException {

        if(staff.getJobNo()==null || "".equals(staff.getJobNo())){
            return new Result(201,null,"工号不能为空");
        }
        if(staff.getEmail()==null || "".equals(staff.getEmail())){
            return new Result(201,null,"工号不能为空");
        }
        Staff s = iStaffDao.getByJobNo(staff.getJobNo());
        if(s==null){
            return new Result(201,null,"工号不存在");
        }
        if(!s.getEmail().equals(staff.getEmail())){
            return new Result(201,null,"邮箱不符");
        }

        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int num = random.nextInt(75) + 48;
            if (num > 57 && num < 65 || num > 90 && num < 97) {
                // 排除58~64、91~96之间的符号，重新生成
                num = random.nextInt(75) + 48;
            }
            char c = (char) num;
            codeBuilder.append(c);
        }

        String to = staff.getEmail(); // 接收人的邮箱地址
        String subject = "找回密码"; // 邮件主题
        String content = "验证码为：" + codeBuilder.toString(); // 邮件内容

        Mail.sendMail(to, subject, content);

        return new Result(200,codeBuilder.toString(),"验证码发送成功");
    }

    @Override
    public Result resetPwd(RequestStaff r){

        if ( r.getPwd()==null || "".equals(r.getPwd()) ) {
            return new Result(201,null,"密码不能为空");
        }
        if ( r.getInputCode()==null || "".equals(r.getInputCode()) ) {
            return new Result(201,null,"验证码不能为空");
        }
        if ( r.getRealCode()==null || "".equals(r.getRealCode()) ) {
            return new Result(400,null,"出错了");
        }
        if (r.getRealCode().equals(r.getInputCode())){
            Login l = new Login();
            l.setJobNo(r.getJobNo());
            l.setPwd(r.getPwd());
            iLoginDao.editPwd(l);
            return new Result(200,null,"密码设置成功");
        }
        else{
            return new Result(202,null,"验证码错误");
        }
    }

    @Override
    public Result editPwd(Login login){
        if ( login.getPwd()==null || "".equals(login.getPwd()) ) {
            return new Result(201,null,"密码不能为空");
        }
        if ( login.getJobNo()==null || "".equals(login.getJobNo()) ) {
            return new Result(201,null,"请登录后重试");
        }
        iLoginDao.editPwd(login);
        return new Result(200,null,"修改密码成功");
    }

    @Override
    public Result login(Login login){

        Login responseLogin = iLoginDao.getByJobNo(login);
        // 判断是否存在用户
        if(responseLogin == null){

            return new Result(201,null,"该用户不存在");
        }
        int requestLogin = Integer.valueOf(login.getPermissions()).intValue();
        int responseLoginPer = Integer.valueOf(responseLogin.getPermissions()).intValue();
        // 比对权限
        if(requestLogin > responseLoginPer){

            return new Result(202,null,"无访问权限");
        }
        // 比对密码
        if(!responseLogin.getPwd().equals(login.getPwd())){

            return new Result(204,null,"密码错误");
        }
        iStaffDao.SetIsOnline(iStaffDao.getByJobNo(responseLogin.getJobNo()));
        Staff responseStaff = iStaffDao.getByJobNo(responseLogin.getJobNo());
        return new Result(200,responseStaff,"登录成功");
    }

    @Override
    public void add(Login user){

        iLoginDao.add(user);
    }

    @Override
    public Result setAdminQuery(){

        return new Result(200,iLoginDao.setAdminQuery(),"请求成功");
    }

    @Override
    public Result editPermissions(Login login){

        if(iLoginDao.querySuperNum()<=1&&login.getPermissions()!='2'){
            return new Result(202,null,"至少要有一个超级管理员");
        }
        iLoginDao.editPermissions(login);
        return new Result(200,iLoginDao.setAdminQuery(),"请求成功");
    }
}
