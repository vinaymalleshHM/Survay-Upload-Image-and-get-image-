package com.tyss.survey;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tyss.survey.controller.AdminController;

@SpringBootApplication
public class SurveyApplication {

	public static void main(String[] args) {
		new File(AdminController.uploadDirectory).mkdir();
		SpringApplication.run(SurveyApplication.class, args);
	}

}
