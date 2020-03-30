package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {

  @Autowired UserRepository<Expert> expertUserRepository;

  @Override
  public List<Expert> findAll() {
    return expertUserRepository.findAll();
  }

  @Override
  public Expert save(Expert expert) {
    return expertUserRepository.save(expert);
  }

  @Override
  public Boolean deleteInBatch(List<Expert> experts) {
    expertUserRepository.deleteInBatch(experts);
    return true;
  }

  @Override
  public Expert findById(Integer id) {
    return expertUserRepository.findById(id).orElse(null);
  }

  @Override
  public Expert findByUsernameAndPassword(String username, String password) {
    return expertUserRepository.findByUsernameAndPassword(username, password);
  }
}
