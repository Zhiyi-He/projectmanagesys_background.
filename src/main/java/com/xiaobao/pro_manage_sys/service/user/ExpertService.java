package com.xiaobao.pro_manage_sys.service.user;

import com.xiaobao.pro_manage_sys.entity.user.Expert;

import java.util.List;

public interface ExpertService {

  /**
   * 通过username和password查询用户信息--登录
   *
   * @param username
   * @param password
   * @return 当前登录用户
   */
  public Expert findByUsernameAndPassword(String username, String password);

  List<Expert> findAll();

  Expert save(Expert expert);

  Boolean deleteInBatch(List<Expert> experts);

  Expert findById(Integer id);
}
