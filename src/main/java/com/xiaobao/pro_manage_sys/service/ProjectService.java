package com.xiaobao.pro_manage_sys.service;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;

import java.util.List;

public interface ProjectService {

  public Project save(Project project);

  public Project findById(Integer id);

  List<Project> findByProStatusAndApplicant(Integer proStatus, Applicant applicant);

  Boolean deleteById(Integer id);

  List<Project> saveAll(List<Project> projects);
}
