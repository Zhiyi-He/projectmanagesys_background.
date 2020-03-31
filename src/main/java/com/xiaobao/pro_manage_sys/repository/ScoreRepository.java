package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.Score;
import com.xiaobao.pro_manage_sys.entity.user.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

  List<Score> findByExpert(Expert expert);
}
