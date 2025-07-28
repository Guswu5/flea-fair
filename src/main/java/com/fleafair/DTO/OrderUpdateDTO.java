package com.fleafair.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderUpdateDTO {
    private Long id;
    private Integer status;
    private LocalDateTime updateTime;
}
