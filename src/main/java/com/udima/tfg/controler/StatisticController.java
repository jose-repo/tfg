package com.udima.tfg.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udima.tfg.core.Constants;
import com.udima.tfg.model.MetaData;
import com.udima.tfg.model.PopulationData;
import com.udima.tfg.model.StatisticData;
import com.udima.tfg.service.PopulationService;
import com.udima.tfg.utils.Util;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The StatisticController class is a REST controller that handles requests related to population statistics.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StatisticController {

  @Autowired private PopulationService populationService;

  @GetMapping("/population-statistic")
  public ResponseEntity<List<StatisticData>> findPopulationByRegions() throws MalformedURLException {
    return ResponseEntity.ok(populationService.populationDataProcessor());
  }
}
