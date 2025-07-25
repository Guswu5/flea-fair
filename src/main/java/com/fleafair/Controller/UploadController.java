package com.fleafair.Controller;

import com.fleafair.Common.Result;
import com.fleafair.Common.Util.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/image")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 上传到OSS的"images/"目录下
            String url = aliOssUtil.upload(file, "images/");
            log.info("上传成功: {}", url);
            return Result.success("上传成功", url);
        } catch (IOException e) {
            return Result.error(500, "上传失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/image")
    public Result<Void> deleteImage(@RequestParam String url) {
        try {
            // 从URL中提取objectName（如将 https://bucket.endpoint/images/abc.jpg 转为 images/abc.jpg）
            String objectName = url.replace("https://" + aliOssUtil.getBucketName() + "." + aliOssUtil.getEndpoint() + "/", "");
            aliOssUtil.delete(objectName);
            return Result.success("删除成功", null);
        } catch (Exception e) {
            return Result.error(500, "删除失败: " + e.getMessage());
        }
    }
}