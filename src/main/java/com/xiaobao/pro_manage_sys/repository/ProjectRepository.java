package com.xiaobao.pro_manage_sys.repository;

import com.xiaobao.pro_manage_sys.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository
    extends JpaRepository<Project, Integer>, JpaSpecificationExecutor<Project> {

  @Query("select p from Project p where p.proStatus in ?1")
  List<Project> findByStatus(List<Integer> status);

  @Query(
      value =
          "select p from Project p where p.applicant.id=?1 and  p.proStatus in ?2 and (?3 is null or p.proName like %?3%)")
  List<Project> findByApplicantId(Integer id, List<Integer> status, String proName);

  @Query(
      value =
          "select p from Project p where p.applicant.repDept.id=?1 and  p.proStatus in ?2 and (?3 is null or p.proName like %?3%)")
  List<Project> findByRepdeptId(Integer id, List<Integer> status, String proName);

  @Query(
      value =
          "select p from Project p where p.applicant.repDept.recDept.id=?1 and  p.proStatus in ?2 and (?3 is null or p.proName like %?3%)")
  List<Project> findByRecdeptId(Integer id, List<Integer> status, String proName);

  @Query(
      value =
          "select p from Project p where p.applicant.id=?1 and  p.proStatus in ?2 and (?3 is null or p.proName like %?3%)")
  Page<Project> findByRecdeptIdPageable(
      Integer id, List<Integer> status, String proName, PageRequest pageRequest);
}
