package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.Applicant;
import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.service.user.RepDeptService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/repDept")
public class RepDeptController {

  Map<String, Object> data = null;
  @Autowired RedisTemplate redisTemplate;

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

  @GetMapping("/rpdInfo")
  public Result getRpdInfo(HttpServletRequest request) {
    data = new HashMap<>();

    // 从请求头中获取token值
    String token = request.getHeader("x-token");

    UserVo cacheUser = (UserVo) redisTemplate.opsForValue().get(token);
    RepDept user = null;
    if (cacheUser != null) {
      user = repDeptService.findById(cacheUser.getId());
    } else {
      return new Result(data, "请登录", 40000);
    }

    if (user != null) {
      data.put("user", user);
      return new Result(data, "获取用户信息成功", 20000);
    } else {
      return new Result(data, "获取用户信息失败", 40000);
    }
  }

  @PutMapping("/rpdInfo")
  public Result updateAppInfo(@RequestBody RepDept repDept) {
    data = new HashMap<>();

    RepDept user = repDeptService.save(repDept);

    if (user != null) {
      data.put("user", user);
      return new Result(data, "修改用户信息成功", 20000);
    } else {
      return new Result(data, "修改用户信息失败", 40000);
    }
  }

  @GetMapping("/applicants")
  public Result getApplicants(HttpServletRequest request) {
    data = new HashMap<>();

    // 从请求头中获取token值
    String token = request.getHeader("x-token");

    UserVo cacheUser = (UserVo) redisTemplate.opsForValue().get(token);
    RepDept user = null;
    if (cacheUser != null) {
      user = repDeptService.findById(cacheUser.getId());
    } else {
      return new Result(data, "请登录", 40000);
    }

    if (user != null) {
      data.put("applicants", user.getApplicants());
      return new Result(data, "获取用户列表成功", 20000);
    } else {
      return new Result(data, "获取用户列表失败", 40000);
    }
  }
}
