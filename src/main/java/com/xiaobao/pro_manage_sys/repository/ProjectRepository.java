package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

  List<Project> findByProStatusAndApplicant(Integer proStatus, Applicant applicant);

  @Query("select p from Project p where p.applicant=?2 and p.proStatus in ?1")
  List<Project> findByStatusAndApplicant(List<Integer> status, Applicant applicant);

  @Query("select p from Project p where p.proStatus in ?1")
  List<Project> findByStatus(List<Integer> status);
}
