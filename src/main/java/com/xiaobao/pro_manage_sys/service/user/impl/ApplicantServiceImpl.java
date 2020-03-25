package com.xiaobao.pro_manage_sys.service.user.impl;

import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.repository.user.ApplicantRepository;
import com.xiaobao.pro_manage_sys.service.user.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

  @Autowired ApplicantRepository applicantRepository;

  @Override
  public Applicant findByUsernameAndPassword(String username, String password) {
    return applicantRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public Applicant save(Applicant applicant) {
    return applicantRepository.save(applicant);
  }

  @Override
  public Applicant findById(Integer id) {
    return applicantRepository.findById(id).orElse(null);
  }

  @Override
  public Boolean deleteInBatch(List<Applicant> applicants) {
    applicantRepository.deleteInBatch(applicants);
    return true;
  }
}
