package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.util.FileUtil;
import com.xiaobao.pro_manage_sys.util.FtpUtil;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FilesController {

  Map<String, Object> data;

  @GetMapping("/download")
  public Result downLoad(
      @PathParam("filename") String filename,
      @PathParam("parentPath") String parentPath,
      HttpServletResponse response)
      throws IOException {
    data = new HashMap<>();
    Boolean flag = FileUtil.downloadFile(parentPath, filename, response);
    if (!flag) {
      return new Result(data, "下载成功", 20000);
    } else {
      return new Result(data, "下载失败", 40000);
    }
  }

  @PostMapping("/upload")
  public Result downLoad(MultipartFile[] files) throws IOException {
    data = new HashMap<>();

    String finalName = FtpUtil.uploadFile(files[0]);
    if (finalName != null) {
      return new Result(data, "上传成功", 20000);
    } else {
      return new Result(data, "上传失败", 40000);
    }
  }
}
