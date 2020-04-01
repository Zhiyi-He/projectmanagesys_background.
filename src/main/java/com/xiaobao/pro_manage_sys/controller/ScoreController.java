package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.entity.Score;
import com.xiaobao.pro_manage_sys.service.ScoreService;
import com.xiaobao.pro_manage_sys.util.Result;
import com.xiaobao.pro_manage_sys.util.UpdateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

  @PostMapping("/score")
  public Result getScoresByProject(@RequestBody Project project) {
    data = new HashMap<>();

    List<Score> scores = scoreService.findByProject(project);

    if (scores != null) {
      data.put("scores", scores);
      return new Result(data, "获取项目评分成功", 20000);
    } else {
      return new Result(data, "获取项目评分失败", 40000);
    }
  }

  @DeleteMapping("/score")
  public Result deleteScores(@RequestBody List<Score> scores) {
    data = new HashMap<>();

    Boolean isDelete = scoreService.deleteScores(scores);

    if (isDelete) {
      return new Result(data, "删除项目评分成功", 20000);
    } else {
      return new Result(data, "删除项目评分成功", 40000);
    }
  }
}
