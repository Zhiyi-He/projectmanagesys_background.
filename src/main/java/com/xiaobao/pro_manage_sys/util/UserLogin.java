package com.xiaobao.pro_manage_sys.util;

import com.xiaobao.pro_manage_sys.entity.user.*;
import com.xiaobao.pro_manage_sys.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserLogin {

    @Autowired
    UserService<Applicant> applicantUserService;
    @Autowired
    UserService<RepDept> repDeptUserService;
    @Autowired
    UserService<RecDept> recDeptUserService;
    @Autowired
    UserService<ProManager> proManagerUserService;
    @Autowired
    UserService<SysManager> sysManagerUserService;
    @Autowired
    UserService<Expert> expertUserService;
    @Autowired
    UserService<DoubleDept> doubleDeptUserService;

    public static UserLogin userLogin;

    @PostConstruct
    public void init(){
        userLogin=this;

    }

    public static User findByUsernameAndPassword(String loginParam,String username,String password){
        User admin=null;
        switch (loginParam){
            case "applicant":
                admin = userLogin.applicantUserService.findByUsernameAndPassword(username, password);
                break;
            case "repDept":
                admin = userLogin.repDeptUserService.findByUsernameAndPassword(username, password);
                break;
            case "recDept":
                admin = userLogin.recDeptUserService.findByUsernameAndPassword(username, password);
                break;
            case "proManager":
                admin = userLogin.proManagerUserService.findByUsernameAndPassword(username, password);
                break;
            case "sysManager":
                admin = userLogin.sysManagerUserService.findByUsernameAndPassword(username, password);
                break;
            case "expert":
                admin = userLogin.expertUserService.findByUsernameAndPassword(username, password);
                break;
            case "doubleDept":
                admin=userLogin.doubleDeptUserService.findByUsernameAndPassword(username,password);
                break;
        }
        return admin;
    }
}
