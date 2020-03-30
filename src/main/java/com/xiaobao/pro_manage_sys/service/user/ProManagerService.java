package com.xiaobao.pro_manage_sys.service.user;

import com.xiaobao.pro_manage_sys.entity.user.ProManager;

public interface ProManagerService {

  /**
   * 通过username和password查询用户信息--登录
   *
   * @param username
   * @param password
   * @return 当前登录用户
   */
  public ProManager findByUsernameAndPassword(String username, String password);

  ProManager save(ProManager user);
}
