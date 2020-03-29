package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.RecDept;
import com.xiaobao.pro_manage_sys.repository.user.UserRepository;
import com.xiaobao.pro_manage_sys.service.user.RecDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecDeptServiceImpl implements RecDeptService {

  @Autowired UserRepository<RecDept> recDeptUserRepository;

  @Override
  public RecDept findByUsernameAndPassword(String username, String password) {
    return recDeptUserRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public List<RecDept> findAll() {
    return recDeptUserRepository.findAll();
  }

  @Override
  public RecDept findById(Integer rcdId) {
    return recDeptUserRepository.findById(rcdId).orElse(null);
  }

  @Override
  public Boolean deleteInBatch(List<RecDept> recDepts) {
    recDeptUserRepository.deleteInBatch(recDepts);
    return true;
  }

  @Override
  public RecDept save(RecDept user) {
    return recDeptUserRepository.save(user);
  }
}
