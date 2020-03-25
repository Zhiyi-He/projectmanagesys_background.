package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.service.ProjectService;
import com.xiaobao.pro_manage_sys.service.user.ApplicantService;
import com.xiaobao.pro_manage_sys.service.user.RepDeptService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import com.xiaobao.pro_manage_sys.util.UpdateUtil;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

  @Resource ApplicantService applicantService;

  @Resource ProjectService projectService;

  @Autowired RepDeptService repDeptService;

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

  @PutMapping("/appInfo")
  public Result updateAppInfo(@RequestBody Applicant applicant) {
    data = new HashMap<>();

    Applicant user = applicantService.save(applicant);

    if (user != null) {
      data.put("user", user);
      return new Result(data, "修改用户信息成功", 20000);
    } else {
      return new Result(data, "修改用户信息失败", 40000);
    }
  }

  @PostMapping("/applicant")
  public Result addApplicant(@RequestBody Applicant applicant, HttpServletRequest request) {
    data = new HashMap<>();
    // 从请求头中获取token值
    String token = request.getHeader("x-token");

    UserVo cacheUser = (UserVo) redisTemplate.opsForValue().get(token);
    // 查询当前申报单位对象
    RepDept repDept = null;
    if (cacheUser != null) {
      repDept = repDeptService.findById(cacheUser.getId());
    } else {
      return new Result(data, "请登录！", 40000);
    }
    // 保存申报人账号信息
    applicant.setUsername((int) ((Math.random() * 9 + 1) * 100000) + "");
    applicant.setPassword("123456");
    applicant.setProNum(0);
    applicant.setRepDept(repDept);
    Applicant user = applicantService.save(applicant);

    if (user != null) {
      data.put("user", user);
      return new Result(data, "添加申报人成功", 20000);
    } else {
      return new Result(data, "添加申报人失败", 40000);
    }
  }

  @DeleteMapping("/applicants")
  public Result deleteApplicants(
      HttpServletRequest request, @RequestBody List<Applicant> applicants) {
    data = new HashMap<>();

    // 从请求头中获取token值
    String token = request.getHeader("x-token");

    UserVo cacheUser = (UserVo) redisTemplate.opsForValue().get(token);
    Boolean flag = false;
    if (cacheUser != null) {
      flag = applicantService.deleteInBatch(applicants);
    } else {
      return new Result(data, "请登录", 40000);
    }

    if (flag) {
      return new Result(data, "删除申报人成功", 20000);
    } else {
      return new Result(data, "删除申报人失败", 40000);
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
    Applicant save = applicantService.save(applicant);
    if (save != null) {
      data.put("project", save);
      return new Result(data, "添加项目成功", 20000);
    } else {
      return new Result(data, "添加项目失败", 40000);
    }
  }

  @PutMapping("/project/{currentStep}")
  public Result updateProject(@RequestBody Project project, @PathVariable Integer currentStep) {
    data = new HashMap<>();

    // 项目申报时修改当前状态为初级审核
    if (currentStep == 3) {
      project.setProStatus(3);
    }

    // 通过id获取当前project对象
    Project byId = projectService.findById(project.getId());
    // 复制想要更改的字段值project-->byId
    BeanUtils.copyProperties(project, byId, UpdateUtil.getNullPropertyNames(project));
    // 保存项目信息
    Project save = projectService.save(byId);

    if (save != null) {
      data.put("project", save);
      return new Result(data, "修改项目成功", 20000);
    } else {
      return new Result(data, "修改项目失败", 40000);
    }
  }

  @GetMapping("/project")
  public Result getLaterProject(HttpServletRequest request) {
    data = new HashMap<>();

    // 从请求头中获取token值
    String token = request.getHeader("x-token");
    UserVo cacheUser = (UserVo) redisTemplate.opsForValue().get(token);
    Project project = null;
    if (cacheUser != null) {
      project =
          projectService.findByProStatusAndApplicant(
              0, applicantService.findById(cacheUser.getId()));
    } else {
      return new Result(data, "请登录", 40000);
    }

    if (project != null) {
      data.put("project", project);
      return new Result(data, "获取当前项目成功", 20000);
    } else {
      return new Result(data, "当前并无需要增加或者修改的项目", 40000);
    }
  }
}
