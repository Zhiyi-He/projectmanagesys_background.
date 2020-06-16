package com.xiaobao.pro_manage_sys.service.user;

import com.xiaobao.pro_manage_sys.entity.user.Applicant;

import java.util.List;

public interface ApplicantService {

  /**
   * 通过username和password查询用户信息--登录
   *
   * @param username
   * @param password
   * @return 当前登录用户
   */
  public Applicant findByUsernameAndPassword(String username, String password);

  public Applicant save(Applicant applicant);

  public Applicant findById(Integer id);

  Boolean deleteInBatch(List<Applicant> applicants);

  List<String> findNamesByRpdNames(List<String> rpdNames);
}
