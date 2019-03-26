package com.alibaba.check;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alibaba.check.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class CheckSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckSysApplication.class, args);
		System.out.println("已启动");
	}


}
