package com.xiaobao.pro_manage_sys.service.impl;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.repository.ProjectRepository;
import com.xiaobao.pro_manage_sys.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Resource ProjectRepository projectRepository;

  @Override
  public Boolean deleteById(Integer id) {
    projectRepository.deleteById(id);
    return true;
  }

  @Override
  public Project save(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public Project findById(Integer id) {
    return projectRepository.findById(id).orElse(null);
  }

  @Override
  public List<Project> findByProStatusAndApplicant(Integer proStatus, Applicant applicant) {
    return projectRepository.findByProStatusAndApplicant(proStatus, applicant);
  }

  @Override
  public List<Project> saveAll(List<Project> projects) {
    return projectRepository.saveAll(projects);
  }

  @Override
  public List<Project> findByStatusAndApplicant(List<Integer> status, Applicant applicant) {
    return projectRepository.findByStatusAndApplicant(status, applicant);
  }
}
