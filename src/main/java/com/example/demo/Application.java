package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/*root servlet-context 역할*/
@SpringBootApplication
@MapperScan(value = "com.example.demo.mapper")
public class Application {

	public static final String APPLICATION_LOCATIONS ="spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:aws.yml,"
			+ "classpath:/application.properties";

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}


