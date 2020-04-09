package com.xiaobao.pro_manage_sys.service.user;

import com.xiaobao.pro_manage_sys.entity.user.SysManager;

public interface SysManagerService {

  /**
   * 通过username和password查询用户信息--登录
   *
   * @param username
   * @param password
   * @return 当前登录用户
   */
  public SysManager findByUsernameAndPassword(String username, String password);

  SysManager save(SysManager user);
}
