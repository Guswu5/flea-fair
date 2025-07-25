package com.fleafair;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:secrets.properties", ignoreResourceNotFound = true)
@MapperScan("com.fleafair.Mapper") // 扫描Mapper接口
public class FleaFairApplication {
	public static void main(String[] args) {
		SpringApplication.run(FleaFairApplication.class, args);
	}

}
