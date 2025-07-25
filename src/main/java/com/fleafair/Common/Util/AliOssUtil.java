package com.fleafair.Common.Util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@Data  // 使用Lombok自动生成getter/setter
public class AliOssUtil {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    /**
     * 上传文件到OSS
     * @param file 上传的文件对象
     * @param folder 存储文件夹（如 "images/"）
     * @return 文件访问URL
     */
    public String upload(MultipartFile file, String folder) throws IOException {
        // 1. 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 2. 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = folder + UUID.randomUUID() + fileExtension;

            // 3. 上传文件流
            ossClient.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream()));

            // 4. 返回文件访问URL
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } finally {
            // 5. 关闭OSS客户端
            ossClient.shutdown();
        }
    }

    /**
     * 删除OSS文件
     * @param objectName 文件路径（如 "images/abc.jpg"）
     */
    public void delete(String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.deleteObject(bucketName, objectName);
        } finally {
            ossClient.shutdown();
        }
    }
}