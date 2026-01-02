package com.maxim.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.maxim.mybatisplus.mapper")
public class mybatisplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(mybatisplusApplication.class, args);
	}

}
