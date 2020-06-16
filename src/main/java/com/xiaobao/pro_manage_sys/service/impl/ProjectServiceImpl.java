package com.xiaobao.pro_manage_sys.service.impl;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.repository.ProjectRepository;
import com.xiaobao.pro_manage_sys.repository.ScoreRepository;
import com.xiaobao.pro_manage_sys.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Resource ProjectRepository projectRepository;
  @Resource ScoreRepository scoreRepository;

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
  public Page<Project> findByPage(
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
      PageRequest pageRequest) {
    // 规格定义
    Specification<Project> specification =
        new Specification<Project>() {

          /**
           * 构造断言
           *
           * @param root 实体对象引用
           * @param query 规则查询对象
           * @param cb 规则构建对象
           * @return 断言
           */
          @Override
          public Predicate toPredicate(
              Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>(); // 所有的断言
            // 添加项目状态条件
            Predicate inStatus = cb.and(root.get("proStatus").as(Integer.class).in(status));
            predicates.add(inStatus);
            // 添加项目类型条件
            if (proTypes != null) {
              Predicate inProTypes = cb.and(root.get("proType").as(String.class).in(proTypes));
              predicates.add(inProTypes);
            }
            // 添加学科分类条件
            if (subjects != null) {
              Predicate inSubjects = cb.and(root.get("subject").as(String.class).in(subjects));
              predicates.add(inSubjects);
            }
            // 添加不同用户处理条件
            if (userType != null) {
              switch (userType) {
                case "applicant":
                  Predicate equalApplicant =
                      cb.equal(root.join("applicant").get("id").as(Integer.class), id);
                  predicates.add(equalApplicant);
                  break;
                case "repDept":
                  Predicate equalRepdept =
                      cb.equal(
                          root.join("applicant").join("repDept").get("id").as(Integer.class), id);
                  predicates.add(equalRepdept);
                  break;
                case "recDept":
                  Predicate equalRecdept =
                      cb.equal(
                          root.join("applicant")
                              .join("repDept")
                              .join("recDept")
                              .get("id")
                              .as(Integer.class),
                          id);
                  predicates.add(equalRecdept);
                  break;
                case "expert":
                  List<Integer> ids = scoreRepository.findByExpId(id, isScore);
                  Predicate inExpertIds = cb.and(root.get("id").as(Integer.class).in(ids));
                  predicates.add(inExpertIds);
              }
            }
            // 添加项目名称模糊查询条件
            if (proName != null) {
              Predicate likeProName =
                  cb.like(root.get("proName").as(String.class), "%" + proName + "%");
              predicates.add(likeProName);
            }
            // 添加申报人名称多选查询条件
            if (appNames != null) {
              Predicate inAppNames =
                  cb.and(root.join("applicant").get("name").as(String.class).in(appNames));
              predicates.add(inAppNames);
            }
            // 添加申报单位名称多选查询条件
            if (rpdNames != null) {
              Predicate inRpdNames =
                  cb.and(
                      root.join("applicant")
                          .join("repDept")
                          .get("deptName")
                          .as(String.class)
                          .in(rpdNames));
              predicates.add(inRpdNames);
            }
            // 添加推荐单位名称多选查询条件
            if (rcdNames != null) {
              Predicate inRcdNames =
                  cb.and(
                      root.join("applicant")
                          .join("repDept")
                          .join("recDept")
                          .get("deptName")
                          .as(String.class)
                          .in(rcdNames));
              predicates.add(inRcdNames);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
          }
        };
    // 查询
    return projectRepository.findAll(specification, pageRequest);
  }

  @Override
  public List<Project> saveAll(List<Project> projects) {
    return projectRepository.saveAll(projects);
  }

  @Override
  public List<Project> findByUserId(
      String userType, Integer id, List<Integer> status, String proName, PageRequest pageRequest) {
    if (userType == null) {
      return projectRepository.findByStatus(status);
    } else {
      switch (userType) {
        case "applicant":
          return projectRepository.findByApplicantId(id, status, proName);
        case "repDept":
          return projectRepository.findByRepdeptId(id, status, proName);
        case "recDept":
          return projectRepository.findByRecdeptId(id, status, proName);
        default:
          return null;
      }
    }
  }
}
