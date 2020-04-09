package com.xiaobao.pro_manage_sys.controller;

import com.xiaobao.pro_manage_sys.entity.LocalFile;
import com.xiaobao.pro_manage_sys.service.FileService;
import com.xiaobao.pro_manage_sys.util.FileUtil;
import com.xiaobao.pro_manage_sys.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FilesController {

  @Autowired FileService fileService;

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
  public Result upload(
      @RequestParam("files") MultipartFile[] files,
      @RequestParam(name = "title", required = false) String title,
      @RequestParam(name = "fileType", required = false) Integer fileType)
      throws IOException {
    data = new HashMap<>();
    LocalFile[] localFiles = new LocalFile[files.length];
    String lastPath = "";

    switch (fileType) {
      case 0:
        lastPath = "notice";
        break;
      case 1:
        lastPath = "manual";
        break;
      default:
        lastPath = "project";
        break;
    }

    for (int i = 0; i < files.length; i++) {
      LocalFile localFile = FileUtil.uploadFile(files[i], title, lastPath);
      if (localFile != null) {
        localFile.setTitle(title);
        localFile.setFileType(fileType);
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
