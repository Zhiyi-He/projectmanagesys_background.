package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.repository.user.RepDeptRepository;
import com.xiaobao.pro_manage_sys.service.user.RepDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepDeptServiceImpl implements RepDeptService {

  @Autowired RepDeptRepository repDeptRepository;

  @Override
  public RepDept findById(Integer id) {
    return repDeptRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean deleteInBatch(List<RepDept> repDepts) {
    repDeptRepository.deleteInBatch(repDepts);
    return true;
  }

  @Override
  public List<RepDept> saveAll(List<RepDept> repDepts) {
    return repDeptRepository.saveAll(repDepts);
  }

  @Override
  public List<String> findNamesByRcdNames(List<String> rcdNames) {
    return repDeptRepository.findNamesByRcdNames(rcdNames);
  }

  @Override
  public List<RepDept> findByRpdStatus(Integer status) {
    return repDeptRepository.findByRpdStatus(status);
  }

  @Override
  public RepDept save(RepDept repDept) {
    return repDeptRepository.save(repDept);
  }

  @Override
  public RepDept findByUsernameAndPassword(String username, String password) {
    return repDeptRepository.findByUsernameAndPassword(username, password);
  }
}
