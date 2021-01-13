package com.centene.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *  Main Class to start spring boot application
 * @author Srikanth Palutla
 *
 */
@SpringBootApplication
@ComponentScan
public class EnrollmentApplication
{
	public static void main(String[] args) {
		SpringApplication.run(EnrollmentApplication.class, args);
	}
}
