package com.iam.chobo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ServletComponentScan // filter scan
@CrossOrigin
@EnableAspectJAutoProxy
public class ChoboApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoboApplication.class, args);
	}

}
