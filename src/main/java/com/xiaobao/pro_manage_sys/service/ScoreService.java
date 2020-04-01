package com.xiaobao.pro_manage_sys.service;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.Score;
import com.xiaobao.pro_manage_sys.entity.user.Expert;

import java.util.List;

public interface ScoreService {

  List<Score> findByExpert(Expert expert);

  Score save(Score score);

  Score findById(Integer id);

  List<Score> findByProject(Project project);

  Boolean deleteScores(List<Score> scores);
}
