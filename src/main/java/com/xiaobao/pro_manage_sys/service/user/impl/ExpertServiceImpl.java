package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.repository.user.ExpertRepository;
import com.xiaobao.pro_manage_sys.service.user.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {

  @Autowired ExpertRepository expertRepository;

  @Override
  public List<Expert> findAll() {
    return expertRepository.findAll();
  }

  @Override
  public Expert save(Expert expert) {
    return expertRepository.save(expert);
  }

  @Override
  public Boolean deleteInBatch(List<Expert> experts) {
    expertRepository.deleteInBatch(experts);
    return true;
  }

  @Override
  public Expert findById(Integer id) {
    return expertRepository.findById(id).orElse(null);
  }

  @Override
  public List<Expert> saveAll(List<Expert> experts) {
    return expertRepository.saveAll(experts);
  }

  @Override
  public List<Expert> findLimitNum(Integer expertNum) {
    if (expertNum == null) {
      return expertRepository.findAll();
    } else {
      return expertRepository.findLimitNum(expertNum);
    }
  }

  @Override
  public Expert findByUsernameAndPassword(String username, String password) {
    return expertRepository.findByUsernameAndPassword(username, password);
  }
}
