package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.SysManager;
import com.xiaobao.pro_manage_sys.service.user.SysManagerService;
import com.xiaobao.pro_manage_sys.util.JsonXMLUtils;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sysManager")
public class SysManagerController {

  Map<String, Object> data = null;

  @Autowired SysManagerService sysManagerService;

  @PutMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();

    SysManager oldAccount =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("oldAccount"), SysManager.class);
    String newPassword = (String) map.get("newPassword");

    SysManager user =
        sysManagerService.findByUsernameAndPassword(
            oldAccount.getUsername(), oldAccount.getPassword());
    SysManager newUser = null;
    if (user != null) {
      user.setPassword(newPassword);
      newUser = sysManagerService.save(user);
    }

    if (newUser != null) {
      data.put("user", newUser);
      return new Result(data, "修改密码成功", 20000);
    } else {
      return new Result(data, "修改密码失败", 40000);
    }
  }
}
