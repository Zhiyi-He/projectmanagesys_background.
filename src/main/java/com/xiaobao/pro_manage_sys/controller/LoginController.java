package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.util.Result;
import com.xiaobao.pro_manage_sys.util.UserLogin;
import com.xiaobao.pro_manage_sys.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class LoginController {

  @Autowired RedisTemplate redisTemplate;

  Map<String, Object> data = null;

  @PostMapping("/login/{loginParam}")
  public Result findByUsernameAndPassword(
      @PathVariable("loginParam") String loginParam, @RequestBody Map<String, Object> map) {
    data = new HashMap<>();

    String username = (String) map.get("username");
    String password = (String) map.get("password");
    UserVo admin = UserLogin.findByUsernameAndPassword(loginParam, username, password);

    if (admin != null) {
      // 生成Token令牌
      String token = UUID.randomUUID() + "";
      redisTemplate.opsForValue().set(token, admin, Duration.ofMinutes(30L));

      data.put("user", admin);
      data.put("userType", loginParam);
      data.put("token", token);

      return new Result(data, "用户登录成功", 20000);
    } else {
      return new Result(data, "用户名或者密码错误", 40000);
    }
  }

  @GetMapping("/userInfo")
  public Result getUserInfo(HttpServletRequest request) {
    data = new HashMap<>();

    // 从请求头中获取token值
    String token = request.getHeader("x-token");

    Object userVo = redisTemplate.opsForValue().get(token);

    if (userVo != null) {
      data.put("userVo", userVo);
      return new Result(data, "获取登录用户成功", 20000);
    } else {
      return new Result(data, "请登录", 40000);
    }
  }

  @PostMapping("/logout")
  public Result logout(HttpServletRequest request) {
    data = new HashMap<>();

    // 从请求头中获取token值
    String token = request.getHeader("x-token");

    boolean flag = redisTemplate.delete(token);
    if (flag) {
      return new Result(data, "注销用户成功", 20000);
    } else {
      return new Result(data, "注销用户失败", 40000);
    }
  }
}
