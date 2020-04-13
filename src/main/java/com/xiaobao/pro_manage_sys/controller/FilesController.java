package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.LocalFile;
import com.xiaobao.pro_manage_sys.entity.Project;
import com.xiaobao.pro_manage_sys.service.FileService;
import com.xiaobao.pro_manage_sys.service.ProjectService;
import com.xiaobao.pro_manage_sys.util.FileUtil;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FilesController {

  @Autowired FileService fileService;
  @Autowired ProjectService projectService;

  Map<String, Object> data;

  @GetMapping("/files/{fileType}")
  public Result getFilesByType(@PathVariable List<Integer> fileType) {
    data = new HashMap<>();
    List<LocalFile> files = fileService.findByFileType(fileType);
    if (files != null) {
      data.put("files", files);
      return new Result(data, "获取文件成功", 20000);
    } else {
      return new Result(data, "获取文件失败", 40000);
    }
  }

  @DeleteMapping("/files")
  public Result deleteFiles(@RequestBody List<LocalFile> files) throws IOException {
    data = new HashMap<>();
    Boolean flag = fileService.deleteFiles(files) && FileUtil.deleteFiles(files);
    if (flag) {
      return new Result(data, "删除文件成功", 20000);
    } else {
      return new Result(data, "删除文件失败", 40000);
    }
  }

  @GetMapping("/download")
  public Result downLoad(@RequestParam("path") String path, HttpServletResponse response)
      throws IOException {
    data = new HashMap<>();
    Boolean flag = FileUtil.downloadFile(path, response);

    if (!flag) {
      return new Result(data, "下载成功", 20000);
    } else {
      return new Result(data, "下载失败", 40000);
    }
  }

  @PostMapping("/upload")
  public Result upload(
      @RequestParam("files") MultipartFile[] files,
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "fileType", required = false) Integer fileType,
      @RequestParam(name = "proId", required = false) Integer proId)
      throws IOException {
    data = new HashMap<>();
    LocalFile[] localFiles = new LocalFile[files.length];
    String lastPath = "";
    Project project = null;

    if (fileType == null) {
      lastPath = "project/" + proId;
      project = projectService.findById(proId);
    } else {
      switch (fileType) {
        case 0:
          lastPath = "notice";
          break;
        case 1:
          lastPath = "manual";
          break;
      }
    }

    for (int i = 0; i < files.length; i++) {
      LocalFile localFile = FileUtil.uploadFile(files[i], title, lastPath);
      if (localFile != null) {
        localFile.setFileType(fileType);
        localFile.setProject(project);
        localFiles[i] = fileService.save(localFile);
      }
    }

    if (localFiles[files.length - 1] != null) {
      data.put("files", localFiles);
      return new Result(data, "上传成功", 20000);
    } else {
      return new Result(data, "上传失败", 40000);
    }
  }
}
