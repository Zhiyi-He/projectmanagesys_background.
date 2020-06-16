package com.xiaobao.pro_manage_sys.util;

import com.xiaobao.pro_manage_sys.entity.LocalFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileUtil {

  public static Boolean downloadFile(String path, HttpServletResponse response) throws IOException {
    File file = null;
    file = new File(path);
    String filename = path.substring(path.lastIndexOf("/") + 1);
    if (file.exists()) { // 判断文件父目录是否存在
      response.setContentType("application/msword;charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      //             response.setContentType("application/force-download");
      response.setHeader(
          "Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
      byte[] buffer = new byte[1024];
      FileInputStream fis = null; // 文件输入流
      BufferedInputStream bis = null;

      OutputStream os = null; // 输出流
      try {
        os = response.getOutputStream();
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        int i = bis.read(buffer);
        while (i != -1) {
          os.write(buffer, 0, i);
          i = bis.read(buffer);
        }

        return true;
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("----------file download---" + filename);
      try {
        bis.close();
        fis.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public static LocalFile uploadFile(MultipartFile file, String title, String lastPath)
      throws IOException {
    LocalFile localFile = null;

    // 获取上传的文件名
    String oldFileName = file.getOriginalFilename();
    // 截取后缀
    String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
    //    // 使用UUID拼接后缀，定义一个不重复的文件名
    //    String finalName = UUID.randomUUID() + suffix;
    if (title == null) {
      title = oldFileName.substring(0, oldFileName.lastIndexOf("."));
    }
    String name = title + suffix;

    String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    //    String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files/" +
    // lastPath;
    String realPath =
        "/F:/xiaobao/dazhu/IDEA/pro_manage_sys/target/classes//static/files/" + lastPath;
    File dest = new File(realPath);

    try {
      if (!dest.exists()) dest.mkdirs();
      file.transferTo(new File(dest, name));
      localFile =
          new LocalFile(
              oldFileName,
              name,
              title,
              suffix,
              file.getSize(),
              file.getContentType(),
              dateFormat,
              realPath + '/' + name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return localFile;
  }

  public static Boolean deleteFiles(List<LocalFile> files) throws IOException {
    try {
      for (LocalFile file : files) {
        File delFile = new File(file.getPath());
        delFile.delete();
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
