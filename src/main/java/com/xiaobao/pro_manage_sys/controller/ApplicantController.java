package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.service.ProjectService;
import com.xiaobao.pro_manage_sys.service.user.ApplicantService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

  @Resource ApplicantService applicantService;

  @Resource ProjectService projectService;

  @Resource RedisTemplate redisTemplate;

  Map<String, Object> data = null;

  @PutMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();

    Applicant oldAccount =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("oldAccount"), Applicant.class);
    String newPassword = (String) map.get("newPassword");

    Applicant user =
        applicantService.findByUsernameAndPassword(
            oldAccount.getUsername(), oldAccount.getPassword());
    Applicant newUser = null;
    if (user != null) {
      user.setPassword(newPassword);
      newUser = applicantService.save(user);
    }

    if (newUser != null) {
      data.put("user", newUser);
      return new Result(data, "修改密码成功", 20000);
    } else {
      return new Result(data, "修改密码失败", 40000);
    }
  }

  @GetMapping("/appInfo/{id}")
  public Result getAppInfo(@PathVariable Integer id) {
    data = new HashMap<>();

    Applicant user = applicantService.findById(id);
    // 更正个人项目数量
    if (user.getProNum() != user.getProList().size()) {
      user.setProNum(user.getProList().size());
      applicantService.save(user);
    }

    if (user != null) {
      data.put("user", user);
      return new Result(data, "获取用户信息成功", 20000);
    } else {
      return new Result(data, "获取用户信息失败", 40000);
    }
  }

  @PostMapping("/applicant")
  public Result addApplicant(@RequestBody Applicant applicant) {
    data = new HashMap<>();

    // 保存申报人账号信息
    applicant.setUsername((int) ((Math.random() * 9 + 1) * 100000) + "");
    applicant.setPassword("123456");
    applicant.setProNum(0);
    Applicant addUser = applicantService.save(applicant);

    if (addUser != null) {
      data.put("addUser", addUser);
      return new Result(data, "添加申报人成功", 20000);
    } else {
      return new Result(data, "添加申报人失败", 40000);
    }
  }

  @DeleteMapping("/applicants")
  public Result deleteApplicants(@RequestBody List<Applicant> applicants) {
    data = new HashMap<>();

    Boolean flag = applicantService.deleteInBatch(applicants);

    if (flag) {
      return new Result(data, "删除申报人成功", 20000);
    } else {
      return new Result(data, "删除申报人失败", 40000);
    }
  }

  @PutMapping("/appInfo")
  public Result updateAppInfo(@RequestBody Applicant applicant) {
    data = new HashMap<>();

    Applicant updateUser = applicantService.save(applicant);

    if (updateUser != null) {
      data.put("updateUser", updateUser);
      return new Result(data, "修改用户信息成功", 20000);
    } else {
      return new Result(data, "修改用户信息失败", 40000);
    }
  }

  @GetMapping("/proList/{id}")
  public Result getProList(@PathVariable Integer id) {
    data = new HashMap<>();

    Applicant user = applicantService.findById(id);
    Set<Project> proList = user.getProList();

    for (Project project : proList) {
      if (project.getProStatus() == 0) {
        proList.remove(project);
        break;
      }
    }

    if (proList != null) {
      data.put("proList", user.getProList());
      return new Result(data, "获取项目列表成功", 20000);
    } else {
      return new Result(data, "获取项目列表失败", 40000);
    }
  }

  @PostMapping("/project/{appId}")
  public Result addProject(@RequestBody Project project, @PathVariable Integer appId) {
    data = new HashMap<>();

    // 获取当前用户对象
    Applicant applicant = applicantService.findById(appId);
    // 关联申报人对象
    project.setApplicant(applicant);
    // 设置项目当前状态
    project.setProStatus(0);
    // 保存项目信息
    applicant.getProList().add(project);
    Applicant addProject = applicantService.save(applicant);
    if (addProject != null) {
      data.put("addProjectInfo", addProject);
      return new Result(data, "添加项目成功", 20000);
    } else {
      return new Result(data, "添加项目失败", 40000);
    }
  }

  @PutMapping("/projects")
  public Result updateProjects(@RequestBody List<Project> projects) {
    data = new HashMap<>();

    // 保存项目信息
    List<Project> updatePros = projectService.saveAll(projects);

    if (updatePros != null) {
      data.put("updatePros", updatePros);
      return new Result(data, "修改项目成功", 20000);
    } else {
      return new Result(data, "修改项目失败", 40000);
    }
  }

  @DeleteMapping("/project/{id}")
  public Result deleteProject(@PathVariable Integer id) {
    data = new HashMap<>();

    // 删除项目
    Boolean isDelete = projectService.deleteById(id);

    if (isDelete) {
      return new Result(data, "删除项目成功", 20000);
    } else {
      return new Result(data, "删除项目失败", 40000);
    }
  }

  @PostMapping("/projects")
  public Result getProjectsByStatus(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();
    Applicant applicant =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("applicant"), Applicant.class);
    List<Integer> status = (List<Integer>) map.get("status");
    List<Project> projects = projectService.findByStatusAndApplicant(status, applicant);

    data.put("projects", projects);
    return new Result(data, "", 20000);
  }
}
