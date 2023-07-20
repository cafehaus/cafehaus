package com.sky.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
    /**
     * 获取文件保存路径
     * 参考：https://www.bbsmax.com/A/GBJrE67Wz0/
     *
     * @return File
     * @throws FileNotFoundException
     */
    static File getUploadDirectory() throws FileNotFoundException {
        // 开发环境 获取 target/classes 目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());

        // 生产环境 不存在 target/classes 目录
        if (!path.exists()) {
            // 获取当前运行目录
            path = new File("");
        }

        File upload = new File(path.getAbsolutePath(), "public/upload");

        // 不存在则创建
        if (!upload.exists()) {
            upload.mkdirs();
        }

        return upload;
    }

    // TODO 上传路径这块后面要优化下
    // 上传文件目录
    // private static final String UPLOAD_FOLDER = System.getProperty("user.dir") + "/upload/";
    // private static final String UPLOAD_FOLDER = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/";

    public static String upload(MultipartFile myFile, String dir) throws IOException {
        String filePath = "";
        if (!myFile.isEmpty()) {
            try {
                String filename = myFile.getOriginalFilename();
                filename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));

//                File upDir = new File(UPLOAD_FOLDER);
//                // 目录不存在就新建一个
//                if (!upDir.exists()) {
//                    upDir.mkdir();
//                }
//
//                filePath = UPLOAD_FOLDER + filename;
//                File f = new File(filePath);
//                // 通过transferTo方法写入文件，类似 node 里的 fs.writeFile
//                myFile.transferTo(f);
//
//                // filePath = "http://localhost:8080/static/" + filename;
                File f = new File(getUploadDirectory(), filename);
                myFile.transferTo(f);
                filePath = "http://localhost:8080/upload/" + filename;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return filePath;
    }
}
