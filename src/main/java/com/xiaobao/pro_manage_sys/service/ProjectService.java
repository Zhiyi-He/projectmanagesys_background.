package com.xiaobao.pro_manage_sys.service;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;

public interface ProjectService {

  public Project save(Project project);

  public Project findById(Integer id);

  Project findByProStatusAndApplicant(Integer proStatus, Applicant applicant);
}
