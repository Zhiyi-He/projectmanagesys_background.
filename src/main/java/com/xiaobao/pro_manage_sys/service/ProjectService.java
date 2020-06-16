package com.xiaobao.pro_manage_sys.service;

import com.xiaobao.pro_manage_sys.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProjectService {

  public Project save(Project project);

  public Project findById(Integer id);

  Boolean deleteById(Integer id);

  List<Project> saveAll(List<Project> projects);

  List<Project> findByUserId(
      String userType, Integer id, List<Integer> status, String proName, PageRequest pageRequest);

  Page<Project> findByPage(
      String userType,
      Integer id,
      List<Integer> status,
      String proName,
      Boolean isScore,
      List<String> proTypes,
      List<String> subjects,
      List<String> appNames,
      List<String> rpdNames,
      List<String> rcdNames,
      PageRequest pageRequest);
}
