package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.Expert;
import com.xiaobao.pro_manage_sys.service.user.ExpertService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expert")
public class ExpertController {

  Map<String, Object> data = null;

  @Autowired ExpertService expertService;

  @PutMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();

    Expert oldAccount =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("oldAccount"), Expert.class);
    String newPassword = (String) map.get("newPassword");

    Expert user =
        expertService.findByUsernameAndPassword(oldAccount.getUsername(), oldAccount.getPassword());
    Expert newUser = null;
    if (user != null) {
      user.setPassword(newPassword);
      newUser = expertService.save(user);
    }

    if (newUser != null) {
      data.put("user", newUser);
      return new Result(data, "修改密码成功", 20000);
    } else {
      return new Result(data, "修改密码失败", 40000);
    }
  }

  @GetMapping("/expert/{id}")
  public Result getExpert(@PathVariable Integer id) {
    data = new HashMap<>();

    Expert userInfo = expertService.findById(id);
    if (userInfo != null) {
      data.put("userInfo", userInfo);
      return new Result(data, "获取用户信息成功", 20000);
    } else {
      return new Result(data, "获取用户信息失败", 40000);
    }
  }

  @PostMapping("/expert")
  public Result addExpert(@RequestBody Expert expert) {
    data = new HashMap<>();

    // 保存申报人账号信息
    expert.setUsername(expert.getIdCard());
    expert.setPassword("123456");
    Expert addUser = expertService.save(expert);

    if (addUser != null) {
      data.put("addUser", addUser);
      return new Result(data, "添加评审专家成功", 20000);
    } else {
      return new Result(data, "添加评审专家失败", 40000);
    }
  }

  @DeleteMapping("/expert")
  public Result deleteExperts(@RequestBody List<Expert> experts) {
    data = new HashMap<>();

    Boolean flag = expertService.deleteInBatch(experts);

    if (flag) {
      return new Result(data, "删除评审专家成功", 20000);
    } else {
      return new Result(data, "删除评审专家失败", 40000);
    }
  }

  @PutMapping("/expert")
  public Result updateExperts(@RequestBody List<Expert> experts) {
    data = new HashMap<>();

    List<Expert> updateResult = expertService.saveAll(experts);

    if (updateResult != null) {
      data.put("updateResult", updateResult);
      return new Result(data, "修改用户信息成功", 20000);
    } else {
      return new Result(data, "修改用户信息失败", 40000);
    }
  }

  @GetMapping("/experts")
  public Result getExperts(@RequestParam(name = "expertNum", required = false) Integer expertNum) {
    data = new HashMap<>();

    List<Expert> experts = expertService.findLimitNum(expertNum);

    if (experts != null) {
      data.put("experts", experts);
      return new Result(data, "获取评审专家列表成功", 20000);
    } else {
      return new Result(data, "获取评审专家列表失败", 40000);
    }
  }
}
