package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.ProManager;
import com.xiaobao.pro_manage_sys.service.user.ProManagerService;
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
@RequestMapping("/proManager")
public class ProManagerController {

  Map<String, Object> data = null;

  @Autowired ProManagerService proManagerService;

  @PutMapping("/updatePassword")
  public Result updatePassword(@RequestBody Map<String, Object> map) throws Exception {
    data = new HashMap<>();

    ProManager oldAccount =
        JsonXMLUtils.map2obj((Map<String, Object>) map.get("oldAccount"), ProManager.class);
    String newPassword = (String) map.get("newPassword");

    ProManager user =
        proManagerService.findByUsernameAndPassword(
            oldAccount.getUsername(), oldAccount.getPassword());
    ProManager newUser = null;
    if (user != null) {
      user.setPassword(newPassword);
      newUser = proManagerService.save(user);
    }

    if (newUser != null) {
      data.put("user", newUser);
      return new Result(data, "修改密码成功", 20000);
    } else {
      return new Result(data, "修改密码失败", 40000);
    }
  }
}
