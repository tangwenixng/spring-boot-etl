package com.twx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.twx.db")
public class SpringBootEtlApplication{

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(SpringBootEtlApplication.class);
//	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEtlApplication.class, args);
	}
}
