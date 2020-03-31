package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.Score;
import com.xiaobao.pro_manage_sys.service.ScoreService;
import com.xiaobao.pro_manage_sys.util.Result;
import com.xiaobao.pro_manage_sys.util.UpdateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class ScoreController {

  Map<String, Object> data = null;

  @Autowired ScoreService scoreService;

  @PutMapping("/score")
  public Result updateScore(@RequestBody Score score) {
    data = new HashMap<>();

    Score byId = scoreService.findById(score.getId());
    BeanUtils.copyProperties(score, byId, UpdateUtil.getNullPropertyNames(score));
    Score updateResult = scoreService.save(byId);

    if (updateResult != null) {
      data.put("updateResult", updateResult);
      return new Result(data, "评分该项目成功", 20000);
    } else {
      return new Result(data, "评分该项目失败", 40000);
    }
  }
}
