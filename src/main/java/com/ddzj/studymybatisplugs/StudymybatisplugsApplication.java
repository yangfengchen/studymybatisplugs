package com.ddzj.studymybatisplugs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ddzj.studymybatisplugs.mapper")
public class StudymybatisplugsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudymybatisplugsApplication.class, args);
	}

}
