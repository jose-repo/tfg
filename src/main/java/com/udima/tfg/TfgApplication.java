package com.udima.tfg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udima.tfg.model.OperacionesDisponibles;
import com.udima.tfg.utils.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class TfgApplication {

	public static void main(String[] args) throws MalformedURLException, JsonProcessingException {
		SpringApplication.run(TfgApplication.class, args);
	}

}
