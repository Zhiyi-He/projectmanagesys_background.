package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.RecDept;
import com.xiaobao.pro_manage_sys.service.user.RecDeptService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recDept")
public class RecDeptController {

  Map<String, Object> data = null;

  @Autowired RecDeptService recDeptService;

  @PutMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();

    RecDept oldAccount =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("oldAccount"), RecDept.class);
    String newPassword = (String) map.get("newPassword");

    RecDept user =
        recDeptService.findByUsernameAndPassword(
            oldAccount.getUsername(), oldAccount.getPassword());
    RecDept newUser = null;
    if (user != null) {
      user.setPassword(newPassword);
      newUser = recDeptService.save(user);
    }

    if (newUser != null) {
      data.put("user", newUser);
      return new Result(data, "修改密码成功", 20000);
    } else {
      return new Result(data, "修改密码失败", 40000);
    }
  }

  @GetMapping("/rcdInfo/{rcdId}")
  public Result getRcdInfo(@PathVariable Integer rcdId) {
    data = new HashMap<>();

    RecDept userInfo = recDeptService.findById(rcdId);
    if (userInfo != null) {
      data.put("userInfo", userInfo);
      return new Result(data, "获取用户信息成功", 20000);
    } else {
      return new Result(data, "获取用户信息失败", 40000);
    }
  }

  @PostMapping("/recDept")
  public Result addRecDept(@RequestBody RecDept recDept) {
    data = new HashMap<>();

    // 保存申报人账号信息
    recDept.setUsername(recDept.getDeptCode());
    recDept.setPassword("123456");
    RecDept addUser = recDeptService.save(recDept);

    if (addUser != null) {
      data.put("addUser", addUser);
      return new Result(data, "添加组织推荐单位成功", 20000);
    } else {
      return new Result(data, "添加组织推荐单位失败", 40000);
    }
  }

  @DeleteMapping("/recDepts")
  public Result deleteRecDepts(@RequestBody List<RecDept> recDepts) {
    data = new HashMap<>();

    Boolean flag = recDeptService.deleteInBatch(recDepts);

    if (flag) {
      return new Result(data, "删除组织推荐单位成功", 20000);
    } else {
      return new Result(data, "删除组织推荐单位失败", 40000);
    }
  }

  @PutMapping("/rcdInfo")
  public Result updateRcdInfo(@RequestBody RecDept recDept) {
    data = new HashMap<>();

    RecDept updateUser = recDeptService.save(recDept);

    if (updateUser != null) {
      data.put("updateUser", updateUser);
      return new Result(data, "修改用户信息成功", 20000);
    } else {
      return new Result(data, "修改用户信息失败", 40000);
    }
  }

  @GetMapping("/repDepts/{rcdId}")
  public Result getRepDepts(@PathVariable Integer rcdId) {
    data = new HashMap<>();

    RecDept user = recDeptService.findById(rcdId);

    if (user != null) {
      data.put("repDepts", user.getRepDepts());
      return new Result(data, "获取用户列表成功", 20000);
    } else {
      return new Result(data, "获取用户列表失败", 40000);
    }
  }

  @GetMapping("/recDepts")
  public Result getRecDepts() {
    data = new HashMap<>();

    List<RecDept> recDepts = recDeptService.findAll();

    if (recDepts != null) {
      data.put("recDepts", recDepts);
      return new Result(data, "获取单位列表成功", 20000);
    } else {
      return new Result(data, "获取单位列表失败", 40000);
    }
  }
}
