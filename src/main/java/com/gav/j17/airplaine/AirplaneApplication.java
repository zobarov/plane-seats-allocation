package com.gav.j17.airplaine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gav.j17.airplaine.util.Loggable;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class AirplaneApplication implements CommandLineRunner {
	@Loggable
	private Logger logger;
	@Value("${input.file}")
	private String inputFileName;

	public static void main(String[] args) {
		SpringApplication.run(AirplaneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(args == null || args.length == 0) {
			logger.warn("No input filename specified. Go with resources {}", inputFileName);
		} else {
			logger.debug("Starting wirh filename: {}", args[0]);
		}
				
	}
}
