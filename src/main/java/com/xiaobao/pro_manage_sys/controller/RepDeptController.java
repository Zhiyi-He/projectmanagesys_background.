package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.service.user.RepDeptService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repDept")
public class RepDeptController {

  Map<String, Object> data = null;

  @Resource RepDeptService repDeptService;

  @PutMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();

    Applicant oldAccount =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("oldAccount"), Applicant.class);
    String newPassword = (String) map.get("newPassword");

    RepDept user =
        repDeptService.findByUsernameAndPassword(
            oldAccount.getUsername(), oldAccount.getPassword());
    RepDept newUser = null;
    if (user != null) {
      user.setPassword(newPassword);
      newUser = repDeptService.save(user);
    }

    if (newUser != null) {
      data.put("user", newUser);
      return new Result(data, "修改密码成功", 20000);
    } else {
      return new Result(data, "修改密码失败", 40000);
    }
  }

  @GetMapping("/rpdInfo/{rpdId}")
  public Result getRpdInfo(@PathVariable Integer rpdId) {
    data = new HashMap<>();

    RepDept userInfo = repDeptService.findById(rpdId);
    if (userInfo != null) {
      data.put("userInfo", userInfo);
      return new Result(data, "获取用户信息成功", 20000);
    } else {
      return new Result(data, "获取用户信息失败", 40000);
    }
  }

  @PostMapping("/repDept")
  public Result addRepDept(@RequestBody RepDept repDept) {
    data = new HashMap<>();

    // 保存申报人账号信息
    repDept.setUsername((int) ((Math.random() * 9 + 1) * 100000) + "");
    repDept.setPassword("123456");
    RepDept addUser = repDeptService.save(repDept);

    if (addUser != null) {
      data.put("addUser", addUser);
      return new Result(data, "添加申报单位成功", 20000);
    } else {
      return new Result(data, "添加申报单位失败", 40000);
    }
  }

  @GetMapping("/repDepts/{status}")
  public Result getRepDeptsByRpdStatus(@PathVariable Integer status) {
    data = new HashMap<>();

    List<RepDept> repDepts = repDeptService.findByRpdStatus(status);
    if (repDepts != null) {
      data.put("repDepts", repDepts);
      return new Result(data, "获取待审核申报单位成功", 20000);
    } else {
      return new Result(data, "获取待审核申报单位失败", 40000);
    }
  }

  @DeleteMapping("/repDepts")
  public Result deleteRepDepts(@RequestBody List<RepDept> repDepts) {
    data = new HashMap<>();

    Boolean flag = repDeptService.deleteInBatch(repDepts);

    if (flag) {
      return new Result(data, "删除申报单位成功", 20000);
    } else {
      return new Result(data, "删除申报单位失败", 40000);
    }
  }

  @PutMapping("/repDept")
  public Result updateRepDepts(@RequestBody List<RepDept> repDepts) {
    data = new HashMap<>();

    List<RepDept> updateUsers = repDeptService.saveAll(repDepts);

    if (updateUsers.size() != 0) {
      data.put("updateUsers", updateUsers);
      return new Result(data, "修改申报单位成功", 20000);
    } else {
      return new Result(data, "修改申报单位失败", 40000);
    }
  }

  @GetMapping("/applicants/{rpdId}")
  public Result getApplicants(@PathVariable Integer rpdId) {
    data = new HashMap<>();

    RepDept user = repDeptService.findById(rpdId);

    if (user != null) {
      data.put("applicants", user.getApplicants());
      return new Result(data, "获取用户列表成功", 20000);
    } else {
      return new Result(data, "获取用户列表失败", 40000);
    }
  }
}
