package com.udima.tfg.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udima.tfg.core.Constants;
import com.udima.tfg.model.PopulationData;
import com.udima.tfg.utils.Util;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a repository for population data.
 * It provides methods to retrieve population data by regions or federal states.
 */
@Repository
public class PopulationDataRepository {
    public List<PopulationData> findPopulationByRegions() throws MalformedURLException {
        String strRegionJson = Util.stream(new URL(Constants.STATISTIC_POPULATION_REGION_URL));
        List<PopulationData> od = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            od = objectMapper.readValue(strRegionJson, objectMapper.getTypeFactory().constructCollectionType(List.class, PopulationData.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return od;
    }
    
    public List<PopulationData> findPopulationByFederalStates() throws MalformedURLException {
        String strRegionJson = Util.stream(new URL(Constants.STATISTIC_POPULATION_FEDERAL_STATE));
        List<PopulationData> od = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            od = objectMapper.readValue(strRegionJson, objectMapper.getTypeFactory().constructCollectionType(List.class, PopulationData.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return od;
    }
}
