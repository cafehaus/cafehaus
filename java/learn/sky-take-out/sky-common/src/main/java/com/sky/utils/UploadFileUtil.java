package com.sky.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
    // TODO 上传路径这块后面要优化下
    // 上传文件目录
    // private static final String UPLOAD_FOLDER = System.getProperty("user.dir") + "/upload/";
    private static final String UPLOAD_FOLDER = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/";

    public static String upload(MultipartFile myFile, String dir) throws IOException {
        String filePath = "";
        if (!myFile.isEmpty()) {
            try {
                String filename = myFile.getOriginalFilename();
                filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

                File upDir = new File(UPLOAD_FOLDER);
                // 目录不存在就新建一个
                if (!upDir.exists()) {
                    upDir.mkdir();
                }

                filePath = UPLOAD_FOLDER + filename;
                File f = new File(filePath);
                // 通过transferTo方法写入文件，类似 node 里的 fs.writeFile
                myFile.transferTo(f);

                // filePath = "http://localhost:8080/static/" + filename;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return filePath;
    }
}
