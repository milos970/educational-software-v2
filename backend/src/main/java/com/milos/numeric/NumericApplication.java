package com.milos.numeric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NumericApplication
{
	@Autowired
	private  SystemSettingsService systemSettingsService;


	public static void main(String[] args)
	{

		SpringApplication.run(NumericApplication.class, args);

	}





}

