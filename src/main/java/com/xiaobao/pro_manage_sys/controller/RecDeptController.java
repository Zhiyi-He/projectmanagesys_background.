package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.user.RecDept;
import com.xiaobao.pro_manage_sys.entity.user.RepDept;
import com.xiaobao.pro_manage_sys.service.user.RecDeptService;
import com.xiaobao.pro_manage_sys.util.Option;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recDept")
public class RecDeptController {

  Map<String, Object> data = null;

  @Autowired RecDeptService recDeptService;

  @GetMapping("/deptList")
  public Result getDeptNames() {
    data = new HashMap<>();
    List<Object> deptNames = new ArrayList<>(0);

    List<RecDept> recDepts = recDeptService.findAll();
    for (RecDept recDept : recDepts) {
      Option rcdOption = new Option(recDept.getDeptName(), recDept.getId());
      for (RepDept repDept : recDept.getRepDepts()) {
        Option rpdOption = new Option(repDept.getDeptName(), repDept.getId());
        rpdOption.setChildren(null);
        rcdOption.getChildren().add(rpdOption);
      }
      deptNames.add(rcdOption);
    }

    if (deptNames.size() != 0) {
      data.put("deptNames", deptNames);
      return new Result(data, "获取单位列表成功", 20000);
    } else {
      return new Result(data, "获取单位列表失败", 40000);
    }
  }
}
