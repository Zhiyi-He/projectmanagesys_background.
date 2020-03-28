package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

  List<Project> findByProStatusAndApplicant(Integer proStatus, Applicant applicant);
}
