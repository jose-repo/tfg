package com.udima.tfg.service;

import com.udima.tfg.core.RegionExtension;
import com.udima.tfg.core.RiskLevel;
import com.udima.tfg.factory.PopulationDataFactory;
import com.udima.tfg.model.*;
import com.udima.tfg.repository.PopulationDataRepository;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulationService {
  @Autowired private PopulationDataFactory<FederalStateData> federalStateFactory;
  @Autowired private PopulationDataFactory<RegionData> regionFactory;
  @Autowired private PopulationDataRepository populationDataRepository;

  public List<StatisticData> populationDataProcessor(Integer yearFrom, Integer yearTo)
      throws MalformedURLException {
    List<PopulationData> populationDataList =
        populationDataRepository.findPopulationByFederalStates();
    List<PopulationData> populationRegionDataList =
        populationDataRepository.findPopulationByRegions();
    List<FederalStateData> federalStateDataList = convertToFederalStateData(populationDataList);
    federalStateDataList.forEach(
        federalStateData -> {
          calculateDepopulationValue(federalStateData);
          federalStateData.setRegionDataList(
              convertToRegionData(federalStateData, populationRegionDataList));
        });
    return List.of(StatisticData.builder().federalStateDataList(federalStateDataList).build());
  }

  private List<FederalStateData> convertToFederalStateData(
      @NotNull List<PopulationData> populationDataList) {
    return populationDataList.stream()
        .filter(populationData -> !"00".equals(populationData.metaData.get(0).getCodigo()))
        .filter(populationData -> "Total".equals(populationData.metaData.get(1).getNombre()))
        .map(federalStateFactory::createData)
        .collect(Collectors.toList());
  }

  private List<RegionData> convertToRegionData(
      FederalStateData federalStateData, @NotNull List<PopulationData> populationRegionDataList) {
    List<RegionExtension.RegionExtensionEnum> regionsToFind =
        Arrays.stream(RegionExtension.RegionExtensionEnum.values())
            .filter(
                regionExtensionEnum ->
                    regionExtensionEnum.codeFederalState != null
                        && regionExtensionEnum.codeFederalState.equals(
                            federalStateData.metaData.get(0).getCodigo()))
            .toList();
    List<RegionData> regionDataResult = populationRegionDataList.stream()
        .filter(populationData -> !"00".equals(populationData.metaData.get(0).getCodigo()))
        .filter(populationData -> "Total".equals(populationData.metaData.get(1).getNombre()))
        .filter(
            populationData ->
                regionsToFind.stream()
                    .anyMatch(
                        regionExtensionEnum ->
                            regionExtensionEnum.code.equals(
                                populationData.metaData.get(0).getCodigo())))
        .map(regionFactory::createData)
        .collect(Collectors.toList());
    regionDataResult.forEach(this::calculateDepopulationValue);
    return regionDataResult;
  }

  private void calculateDepopulationValue(FederalStateData federalStateData) {
    federalStateData.getData()
              .forEach(
                  data -> {
                    double depopulationValue = data.getValor() / federalStateData.getExtension();
                    data.setPopulationDensity(depopulationValue);
                    calculateRiskIndex(data, depopulationValue);
                  });
  }

  private void calculateDepopulationValue(RegionData regionData) {
    regionData.getData()
            .forEach(
                    data -> {
                      double depopulationValue = data.getValor() / regionData.getExtension();
                      data.setPopulationDensity(depopulationValue);
                      calculateRiskIndex(data, depopulationValue);
                    });
  }

  private void calculateRiskIndex(Data data, double depopulationValue) {
    if (depopulationValue < 12.5) {
      data.setRiskLevel(RiskLevel.RiskLevelEnum.EXTREM.level);
    } else if (depopulationValue < 17) {
      data.setRiskLevel(RiskLevel.RiskLevelEnum.HIGH.level);
    } else if (depopulationValue < 22) {
      data.setRiskLevel(RiskLevel.RiskLevelEnum.MIDDLE.level);
    } else {
      data.setRiskLevel(RiskLevel.RiskLevelEnum.LOW.level);
    }
  }

}
