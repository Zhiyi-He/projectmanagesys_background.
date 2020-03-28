package com.xiaobao.pro_manage_sys.service.user;

import com.xiaobao.pro_manage_sys.entity.user.RecDept;

import java.util.List;

public interface RecDeptService {

  /**
   * 通过username和password查询用户信息--登录
   *
   * @param username
   * @param password
   * @return 当前登录用户
   */
  public RecDept findByUsernameAndPassword(String username, String password);

  List<RecDept> findAll();

  RecDept save(RecDept user);

  RecDept findById(Integer rcdId);
}
