package com.xiaobao.pro_manage_sys.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class FileUtil {

    public static Boolean downloadFile(String parentPath,String filename, HttpServletResponse response) throws IOException {
        String filePath = "src/main/resources/static/files";
        File file=null;
        file = new File(filePath + "/"+parentPath+ "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/msword;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
//             response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer,0,i);
                    i = bis.read(buffer);
                }
                os.flush();

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
}
