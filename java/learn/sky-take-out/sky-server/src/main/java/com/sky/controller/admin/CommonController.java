package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.UploadFileUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 公共请求
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public Result uploadFile(MultipartFile file) throws IOException {
        log.info("上传文件：{}", file);
        String fileUrl = UploadFileUtil.upload(file, "");
        if (fileUrl == null || fileUrl == "") {
            return Result.error("上传失败");
        }
        return Result.success(fileUrl);
    }
}
