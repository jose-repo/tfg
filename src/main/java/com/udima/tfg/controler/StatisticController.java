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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The StatisticController class is a REST controller that handles requests related to population statistics.
 */
@RestController
public class StatisticController {

  @Autowired private PopulationService populationService;

  @GetMapping("/api/population-statistic")
  public ResponseEntity<List<StatisticData>> findPopulationByRegions() throws MalformedURLException {
    return ResponseEntity.ok(populationService.populationDataProcessor(null, null));
  }

  @GetMapping("/api/available-federal-states")
  public ResponseEntity<List<MetaData>> findAvailableFederalStates() throws MalformedURLException {
    String strRegionJson = Util.stream(new URL(Constants.STATISTIC_POPULATION_FEDERAL_STATE));
    List<PopulationData> od;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      od =
          objectMapper.readValue(
              strRegionJson,
              objectMapper
                  .getTypeFactory()
                  .constructCollectionType(List.class, PopulationData.class));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(List.of());
    }
    List<MetaData> data =
        od.stream()
            .map(
                populationData ->
                    populationData.metaData.stream()
                        .filter(metaData -> metaData.variable.id == 70)
                        .findFirst()
                        .get())
            .collect(Collectors.toList());
    return ResponseEntity.ok(
        data.stream()
            .filter(Util.distinctByKey(metaData -> metaData.id))
            .filter(metaData -> !"00".equals(metaData.codigo))
            .collect(Collectors.toList()));
  }

  @GetMapping("/api/available-regions")
  public ResponseEntity<List<MetaData>> findAvailableRegions() throws MalformedURLException {
    String strRegionJson = Util.stream(new URL(Constants.STATISTIC_POPULATION_REGION_URL));
    List<PopulationData> od;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      od =
          objectMapper.readValue(
              strRegionJson,
              objectMapper
                  .getTypeFactory()
                  .constructCollectionType(List.class, PopulationData.class));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body(List.of());
    }
    List<MetaData> data =
        od.stream()
            .flatMap(
                populationData ->
                    populationData.metaData.stream()
                        .filter(metaData -> metaData.variable.id == 115))
            .collect(Collectors.toList());
    return ResponseEntity.ok(
        data.stream()
            .filter(Util.distinctByKey(metaData -> metaData.id))
            .filter(metaData -> !"00".equals(metaData.codigo))
            .collect(Collectors.toList()));
  }
}
