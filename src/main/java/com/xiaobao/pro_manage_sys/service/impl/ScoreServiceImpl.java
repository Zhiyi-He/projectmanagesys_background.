package com.xiaobao.pro_manage_sys.service.impl;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.Score;
import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.repository.ScoreRepository;
import com.xiaobao.pro_manage_sys.service.ScoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

  @Resource ScoreRepository scoreRepository;

  @Override
  public List<Score> findByExpert(Expert expert) {
    return scoreRepository.findByExpert(expert);
  }

  @Override
  public Score save(Score score) {
    return scoreRepository.save(score);
  }

  @Override
  public Score findById(Integer id) {
    return scoreRepository.findById(id).get();
  }

  @Override
  public List<Score> findByProject(Project project) {
    return scoreRepository.findByProject(project);
  }

  @Override
  public Boolean deleteScores(List<Score> scores) {
    scoreRepository.deleteInBatch(scores);
    return true;
  }
}
