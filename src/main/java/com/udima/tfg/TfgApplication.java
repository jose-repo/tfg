package com.udima.tfg;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

@SpringBootApplication
public class TfgApplication {

	public static void main(String[] args) throws MalformedURLException, JsonProcessingException {
		SpringApplication.run(TfgApplication.class, args);
	}

}
