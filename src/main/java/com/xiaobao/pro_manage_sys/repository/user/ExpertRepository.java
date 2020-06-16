package com.xiaobao.pro_manage_sys.repository.user;

import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertRepository extends UserRepository<Expert> {

  @Query(
      "select new com.xiaobao.pro_manage_sys.vo.UserVo(id ,username,password) from Expert where username=?1 and password=?2")
  UserVo findUserVoByUsernameAndPassword(String username, String password);

  @Query(
      value = "SELECT * FROM expert where pro_num<5 ORDER BY  RAND() LIMIT ?1",
      nativeQuery = true)
  List<Expert> findLimitNum(Integer expertNum);
}
