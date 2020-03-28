package com.xiaobao.pro_manage_sys.service.user;

import com.xiaobao.pro_manage_sys.entity.user.RepDept;

import java.util.List;

public interface RepDeptService {

  /**
   * 通过username和password查询用户信息--登录
   *
   * @param username
   * @param password
   * @return 当前登录用户
   */
  public RepDept findByUsernameAndPassword(String username, String password);

  RepDept save(RepDept repDept);

  RepDept findById(Integer id);

  Boolean deleteInBatch(List<RepDept> repDepts);
}
