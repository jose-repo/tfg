package com.udima.tfg.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udima.tfg.core.Constants;
import com.udima.tfg.model.Data;
import com.udima.tfg.model.PopulationData;
import com.udima.tfg.utils.Util;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a repository for population data.
 * It provides methods to retrieve population data by regions or federal states.
 */
@Repository
public class PopulationDataRepository {
    public List<PopulationData> findPopulationByRegions() throws MalformedURLException {
        String strRegionJson = Util.stream(new URL(Constants.STATISTIC_POPULATION_REGION_URL), "region");
        List<PopulationData> od = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            od = objectMapper.readValue(strRegionJson, objectMapper.getTypeFactory().constructCollectionType(List.class, PopulationData.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    od.forEach(populationData -> populationData.setData(populationData.data.stream().sorted(Comparator.comparingInt(Data::getAnyo)).collect(Collectors.toList())));
        return od;
    }
    
    public List<PopulationData> findPopulationByFederalStates() throws MalformedURLException {
        String strRegionJson = Util.stream(new URL(Constants.STATISTIC_POPULATION_FEDERAL_STATE), "federalState");
        List<PopulationData> od = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            od = objectMapper.readValue(strRegionJson, objectMapper.getTypeFactory().constructCollectionType(List.class, PopulationData.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        od.forEach(populationData -> populationData.setData(populationData.data.stream().sorted(Comparator.comparingInt(Data::getAnyo)).collect(Collectors.toList())));
        return od;
    }
}
