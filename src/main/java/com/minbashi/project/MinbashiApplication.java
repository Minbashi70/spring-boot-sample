package com.minbashi.project;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class MinbashiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MinbashiApplication.class, args);
	}

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importUserJob;

	@Override
	public void run(String... args) throws Exception {
		jobLauncher.run(importUserJob, new JobParameters());
	}
}
