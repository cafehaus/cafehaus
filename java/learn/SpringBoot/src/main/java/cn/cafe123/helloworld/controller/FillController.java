package cn.cafe123.helloworld.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FillController {
    // 上传文件目录
    private static final String UPLOAD_FOLDER = System.getProperty("user.dir") + "/upload/";

    // 上传文件
    @PostMapping("upload")
    public String upload(String nickname, MultipartFile file, HttpServletRequest req) throws IOException {
        System.out.println("文件大小：" + file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());

        // String path = req.getServletContext().getRealPath("/upload/");
        // 直接存入 static 目录中
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/";
        saveFile(file, path);
        return "上传文件成功";
    }

    public void saveFile(MultipartFile file, String path) throws IOException {
        System.out.println("项目目录：" + System.getProperty("user.dir"));
        System.out.println("文件上传目录：" + UPLOAD_FOLDER);
        System.out.println("新的文件上传目录：" + path);

        // File upDir = new File(UPLOAD_FOLDER);
        File upDir = new File(path);
        // 目录不存在就新建一个
        if (!upDir.exists()) {
            upDir.mkdir();
        }

        File f = new File(path + file.getOriginalFilename());
        // 通过transferTo方法写入文件，类似 node 里的 fs.writeFile
        file.transferTo(f);
    }
}











