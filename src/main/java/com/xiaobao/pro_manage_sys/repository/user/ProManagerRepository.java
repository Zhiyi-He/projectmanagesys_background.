package com.xiaobao.pro_manage_sys.repository.user;

import com.xiaobao.pro_manage_sys.entity.user.ProManager;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.data.jpa.repository.Query;

public interface ProManagerRepository extends UserRepository<ProManager> {

  @Query(
      "select new com.xiaobao.pro_manage_sys.vo.UserVo(id ,username,password) from ProManager where username=?1 and password=?2")
  UserVo findUserVoByUsernameAndPassword(String username, String password);
}
