package com.fleafair.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUtil {

    // 定义图片存储路径
    private static final String UPLOAD_DIR = "E:\\code\\cjwm\\flea-fair\\src\\main\\resources\\uploads";

    /**
     * 保存图片文件到指定目录，并返回保存后的文件路径
     *
     * @param file 原始图片文件
     * @return 保存后的文件路径
     */
    public static String saveAvatarFile(byte[] file) {
        // 创建目标目录（如果不存在）
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("无法创建上传目录", e);
            }
        }

        // 生成唯一的文件名
        String fileName = UUID.randomUUID().toString() + ".jpg";
        Path filePath = uploadPath.resolve(fileName);

        try {
            // 将文件写入目标路径
            Files.write(filePath, file);
        } catch (IOException e) {
            throw new RuntimeException("保存文件失败", e);
        }

        // 返回相对路径（可用于存储到数据库）
        return "/uploads/" + fileName;
    }
}