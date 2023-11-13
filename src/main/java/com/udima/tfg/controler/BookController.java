package com.udima.tfg.controler;

import com.udima.tfg.Constants;
import com.udima.tfg.utils.Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class BookController {

    @GetMapping("/api/operaciones-disponibles")
    public String findOperacionesDisponibles() throws MalformedURLException {
        String strOperacionesDisponibles = Util.stream(new URL(Constants.INE_OPERACIONES_DISPONIBLES));
        //ObjectMapper objectMapper = new ObjectMapper();
        //List<OperacionesDisponibles> od = objectMapper.readValue(strOperacionesDisponibles, objectMapper.getTypeFactory().constructCollectionType(List.class, OperacionesDisponibles.class));
        return strOperacionesDisponibles;
    }
}
