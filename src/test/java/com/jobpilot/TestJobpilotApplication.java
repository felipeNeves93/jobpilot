package com.jobpilot;

import org.springframework.boot.SpringApplication;

public class TestJobpilotApplication {

	public static void main(String[] args) {
		SpringApplication.from(JobpilotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
