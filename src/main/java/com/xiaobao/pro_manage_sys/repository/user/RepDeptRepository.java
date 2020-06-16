package com.xiaobao.pro_manage_sys.repository.user;

import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepDeptRepository extends UserRepository<RepDept> {

  @Query(
      "select new com.xiaobao.pro_manage_sys.vo.UserVo(id ,username,password) from RepDept where username=?1 and password=?2")
  UserVo findUserVoByUsernameAndPassword(String username, String password);

  List<RepDept> findByRpdStatus(Integer status);

  @Query("select deptName from RepDept where recDept.deptName in ?1")
  List<String> findNamesByRcdNames(List<String> rcdNames);
}
