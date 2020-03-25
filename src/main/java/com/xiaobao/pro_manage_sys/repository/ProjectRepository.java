package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

  Project findByProStatusAndApplicant(Integer proStatus, Applicant applicant);
}
