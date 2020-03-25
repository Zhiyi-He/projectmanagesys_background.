package com.xiaobao.pro_manage_sys.service.impl;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.repository.ProjectRepository;
import com.xiaobao.pro_manage_sys.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Resource ProjectRepository projectRepository;

  @Override
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public Project findById(Integer id) {
    return projectRepository.findById(id).orElse(null);
  }

  @Override
  public Project findByProStatusAndApplicant(Integer proStatus, Applicant applicant) {
    return projectRepository.findByProStatusAndApplicant(proStatus, applicant);
  }
}
