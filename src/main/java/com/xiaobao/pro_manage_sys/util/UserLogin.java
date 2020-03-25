package com.xiaobao.pro_manage_sys.util;

import com.xiaobao.pro_manage_sys.repository.user.*;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserLogin {

  public static UserLogin userLogin;
  @Autowired ApplicantRepository applicantRepository;
  @Autowired RepDeptRepository repDeptRepository;
  @Autowired RecDeptRepository recDeptRepository;
  @Autowired ProManagerRepository proManagerRepository;
  @Autowired SysManagerRepository sysManagerRepository;
  @Autowired ExpertRepository expertRepository;

  public static UserVo findByUsernameAndPassword(
      String loginParam, String username, String password) {
    UserVo admin = null;
    switch (loginParam) {
      case "applicant":
        admin = userLogin.applicantRepository.findUserVoByUsernameAndPassword(username, password);
        break;
      case "repDept":
        admin = userLogin.repDeptRepository.findUserVoByUsernameAndPassword(username, password);
        break;
      case "recDept":
        admin = userLogin.recDeptRepository.findUserVoByUsernameAndPassword(username, password);
        break;
      case "proManager":
        admin = userLogin.proManagerRepository.findUserVoByUsernameAndPassword(username, password);
        break;
      case "sysManager":
        admin = userLogin.sysManagerRepository.findUserVoByUsernameAndPassword(username, password);
        break;
      case "expert":
        admin = userLogin.expertRepository.findUserVoByUsernameAndPassword(username, password);
        break;
    }
    return admin;
  }

  @PostConstruct
  public void init() {
    userLogin = this;
  }
}
