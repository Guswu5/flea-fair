package com.fleafair.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ItemReleaseDTO {
    @NotBlank(message = "标题不能为空")
    @Size(min = 2, max = 50, message = "标题长度需在2-50字符之间")
    private String title;

    @Size(max = 500, message = "描述不能超过500字符")
    private String description;

    @DecimalMin(value = "0.01", message = "价格必须≥0.01")
    private BigDecimal price;

    @NotNull(message = "分类不能为空")
    private Integer categoryId;

    private String location;

    @NotEmpty(message = "至少上传一张图片")
    private MultipartFile[] images;

}
