package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.Score;
import com.xiaobao.pro_manage_sys.entity.user.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

  List<Score> findByExpert(Expert expert);

  List<Score> findByProject(Project project);

  @Query(
      value = "select pro_id from score where exp_id=?1 and if(?2,score=0,1=1)",
      nativeQuery = true)
  List<Integer> findByExpId(Integer id, Boolean isScore);
}
