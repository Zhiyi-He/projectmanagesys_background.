package com.xiaobao.pro_manage_sys;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.repository.ProjectRepository;
import com.xiaobao.pro_manage_sys.repository.user.ApplicantRepository;
import com.xiaobao.pro_manage_sys.repository.user.ProManagerRepository;
import com.xiaobao.pro_manage_sys.service.user.ExpertService;
import com.xiaobao.pro_manage_sys.service.user.RepDeptService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.Serializable;

@SpringBootTest
class ProManageSysApplicationTests implements Serializable {

  @Resource ApplicantRepository applicantRepository;

  @Resource ProManagerRepository proManagerRepository;

  @Resource ProjectRepository projectRepository;

  @Resource ExpertService expertService;

  @Resource RepDeptService repDeptService;

  @Test
  public void erviceTest() {
    Expert expert = expertService.findByUsernameAndPassword("xiaobao520", "123456");
    System.out.println((int) ((Math.random() * 9 + 1) * 100000) + "");
    System.out.println(expert);
  }

  @Test
  public void contextLoads() {
    //        ProManager proManager=new ProManager();
    //        proManager.setId(2);
    //        proManager.setUsername("11");

    //        ProManager user = proManagerRepository.save(proManager);
    Applicant applicant = new Applicant();
    applicant.setId(2);
    Applicant user = applicantRepository.save(applicant);

    System.out.println(user);
  }

  @Test
  public void findProList() {
    Applicant user = applicantRepository.findById(1).orElse(null);
    System.out.println(user);
    //        Project project = projectRepository.findById(1).orElse(null);
    //        System.out.println(project);
  }

  @Test
  public void saveProject() {
    Applicant user = applicantRepository.findById(1).orElse(null);
    Project project = projectRepository.findById(5).orElse(null);
    //        user.getProList().add(project);
    project.setApplicant(user);
    Project save1 = projectRepository.save(project);
    //        Applicant save = applicantRepository.save(user);
  }

  @Test
  public void manyToMany() {
    Project project = projectRepository.findById(10002).get();
    Expert expert = expertService.findById(1);
    RepDept byId = repDeptService.findById(1);
  }
}
