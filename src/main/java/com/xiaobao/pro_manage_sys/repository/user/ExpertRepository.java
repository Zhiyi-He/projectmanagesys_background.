package com.xiaobao.pro_manage_sys.repository.user;

import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.data.jpa.repository.Query;

public interface ExpertRepository extends UserRepository<Expert> {

  @Query(
      "select new com.xiaobao.pro_manage_sys.vo.UserVo(id ,username,password) from Expert where username=?1 and password=?2")
  UserVo findUserVoByUsernameAndPassword(String username, String password);
}
