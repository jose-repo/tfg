package com.udima.tfg.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udima.tfg.core.Constants;
import com.udima.tfg.model.MetaData;
import com.udima.tfg.model.PopulationData;
import com.udima.tfg.model.StatisticData;
import com.udima.tfg.model.UserData;
import com.udima.tfg.service.PopulationService;
import com.udima.tfg.utils.Util;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserAuthenticationController {

  @PostMapping(
      value = "/authenticate",
      consumes = "application/json",
      produces = "application/json")
  public ResponseEntity<UserData> authentication(@RequestBody UserData userData) {
    return ResponseEntity.ok(
        UserData.builder()
            .name("Jose Maria")
            .token(userData.getUsername() + userData.getPassword())
            .timeOut(LocalDateTime.now())
            .username(userData.getUsername())
            .password(userData.getPassword())
            .build());
  }
}
