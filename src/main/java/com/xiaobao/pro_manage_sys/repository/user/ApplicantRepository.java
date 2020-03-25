package com.xiaobao.pro_manage_sys.repository.user;

import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.data.jpa.repository.Query;

public interface ApplicantRepository extends UserRepository<Applicant> {

  @Query(
      "select new com.xiaobao.pro_manage_sys.vo.UserVo(app.id ,app.username,app.password) from Applicant app where app.username=?1 and app.password=?2")
  UserVo findUserVoByUsernameAndPassword(String username, String password);
}
