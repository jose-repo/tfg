package com.udima.tfg.service;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udima.tfg.core.FederalStatesExtension;
import com.udima.tfg.core.RegionExtension;
import com.udima.tfg.core.RiskLevel;
import com.udima.tfg.factory.PopulationDataFactory;
import com.udima.tfg.model.*;
import com.udima.tfg.repository.PopulationDataRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PopulationServiceTest {

  @InjectMocks private PopulationService populationService;

  @Mock private PopulationDataFactory<FederalStateData> federalStateDataFactory;

  @Mock private PopulationDataFactory<RegionData> regionFactory;

  @Mock private PopulationDataRepository populationDataRepository;

  // Variables para datos de prueba
  private List<PopulationData> mockFederalStateDataList;
  private List<PopulationData> mockRegionDataList;

  @BeforeEach
  @SneakyThrows
  public void setup() {
    initTestData();
  }

  @ParameterizedTest
  @ValueSource(ints = {3, 9, 12, 15, 18, 27, 30, 36, 39})
  @SneakyThrows
  public void populationDataProcessorFederalStates_whenRiskLevelIsLow_returnsCorrectStatistics(
      int index) {
    when(populationDataRepository.findPopulationByRegions()).thenReturn(this.mockRegionDataList);
    when(populationDataRepository.findPopulationByFederalStates())
        .thenReturn(this.mockFederalStateDataList);
    when(federalStateDataFactory.createData(any()))
        .thenReturn(createFSData(this.mockFederalStateDataList.get(index)));
    when(regionFactory.createData(any())).thenReturn(createRData(this.mockRegionDataList.get(3)));
    List<StatisticData> statisticDataList = populationService.populationDataProcessor();
    Assert.assertFalse(statisticDataList.isEmpty());
    Assert.assertTrue(
        statisticDataList.get(0).getFederalStateDataList().get(0).getDepopulationRiskLevel()
            == RiskLevel.RiskLevelEnum.LOW.level);
  }

  @ParameterizedTest
  @ValueSource(ints = {6, 21, 24, 33})
  @SneakyThrows
  public void populationDataProcessorFederalStates_whenRiskLevelIsMiddle_returnsCorrectStatistics(
      int index) {
    when(populationDataRepository.findPopulationByRegions()).thenReturn(this.mockRegionDataList);
    when(populationDataRepository.findPopulationByFederalStates())
        .thenReturn(this.mockFederalStateDataList);
    when(federalStateDataFactory.createData(any()))
        .thenReturn(createFSData(this.mockFederalStateDataList.get(index)));
    when(regionFactory.createData(any())).thenReturn(createRData(this.mockRegionDataList.get(3)));
    List<StatisticData> statisticDataList = populationService.populationDataProcessor();
    Assert.assertFalse(statisticDataList.isEmpty());
    Assert.assertTrue(
        statisticDataList.get(0).getFederalStateDataList().get(0).getDepopulationRiskLevel()
            == RiskLevel.RiskLevelEnum.LOW.MIDDLE.level);
  }

  @ParameterizedTest
  @ValueSource(ints = {58, 129, 135})
  @SneakyThrows
  public void populationDataProcessorRegion_whenRiskLevelIsExtrem_returnsCorrectStatistics(
      int index) {
    when(populationDataRepository.findPopulationByRegions()).thenReturn(this.mockRegionDataList);
    when(populationDataRepository.findPopulationByFederalStates())
        .thenReturn(this.mockFederalStateDataList);
    when(federalStateDataFactory.createData(any()))
        .thenReturn(createFSData(this.mockFederalStateDataList.get(3)));
    when(regionFactory.createData(any()))
        .thenReturn(createRData(this.mockRegionDataList.get(index)));
    List<StatisticData> statisticDataList = populationService.populationDataProcessor();
    Assert.assertFalse(statisticDataList.isEmpty());
    Assert.assertTrue(
        statisticDataList
                .get(0)
                .getFederalStateDataList()
                .get(0)
                .getRegionDataList()
                .get(0)
                .getDepopulationRiskLevel()
            == RiskLevel.RiskLevelEnum.EXTREM.level);
  }

  @ParameterizedTest
  @ValueSource(ints = {75})
  @SneakyThrows
  public void populationDataProcessorRegion_whenRiskLevelIsHigh_returnsCorrectStatistics(
          int index) {
    when(populationDataRepository.findPopulationByRegions()).thenReturn(this.mockRegionDataList);
    when(populationDataRepository.findPopulationByFederalStates())
            .thenReturn(this.mockFederalStateDataList);
    when(federalStateDataFactory.createData(any()))
            .thenReturn(createFSData(this.mockFederalStateDataList.get(3)));
    when(regionFactory.createData(any()))
            .thenReturn(createRData(this.mockRegionDataList.get(index)));
    List<StatisticData> statisticDataList = populationService.populationDataProcessor();
    Assert.assertFalse(statisticDataList.isEmpty());
    Assert.assertTrue(
        statisticDataList
                .get(0)
                .getFederalStateDataList()
                .get(0)
                .getRegionDataList()
                .get(0)
                .getDepopulationRiskLevel()
            == RiskLevel.RiskLevelEnum.HIGH.level);
  }

  @ParameterizedTest
  @ValueSource(ints = {6, 9, 12, 15})
  @SneakyThrows
  public void populationDataProcessorRegion_whenRiskLevelIsLow_returnsCorrectStatistics(int index) {
    when(populationDataRepository.findPopulationByRegions()).thenReturn(this.mockRegionDataList);
    when(populationDataRepository.findPopulationByFederalStates())
        .thenReturn(this.mockFederalStateDataList);
    when(federalStateDataFactory.createData(any()))
        .thenReturn(createFSData(this.mockFederalStateDataList.get(3)));
    when(regionFactory.createData(any()))
        .thenReturn(createRData(this.mockRegionDataList.get(index)));
    List<StatisticData> statisticDataList = populationService.populationDataProcessor();
    Assert.assertFalse(statisticDataList.isEmpty());
    Assert.assertTrue(
        statisticDataList
                .get(0)
                .getFederalStateDataList()
                .get(0)
                .getRegionDataList()
                .get(0)
                .getDepopulationRiskLevel()
            == RiskLevel.RiskLevelEnum.LOW.level);
  }

  @ParameterizedTest
  @ValueSource(ints = {3})
  @SneakyThrows
  public void populationDataProcessorRegion_whenRiskLevelIsMiddle_returnsCorrectStatistics(
      int index) {
    when(populationDataRepository.findPopulationByRegions()).thenReturn(this.mockRegionDataList);
    when(populationDataRepository.findPopulationByFederalStates())
        .thenReturn(this.mockFederalStateDataList);
    when(federalStateDataFactory.createData(any()))
        .thenReturn(createFSData(this.mockFederalStateDataList.get(3)));
    when(regionFactory.createData(any()))
        .thenReturn(createRData(this.mockRegionDataList.get(index)));
    List<StatisticData> statisticDataList = populationService.populationDataProcessor();
    Assert.assertFalse(statisticDataList.isEmpty());
    Assert.assertTrue(
        statisticDataList
                .get(0)
                .getFederalStateDataList()
                .get(0)
                .getRegionDataList()
                .get(0)
                .getDepopulationRiskLevel()
            == RiskLevel.RiskLevelEnum.MIDDLE.level);
  }

  @Test
  @SneakyThrows
  public void populationDataProcessor_whenDataIsValid_verifyService() {
    PopulationService mockService = Mockito.mock(PopulationService.class);
    mockService.populationDataProcessor();
    verify(mockService, times(1)).populationDataProcessor();
  }

  private void initTestData() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    String content =
        new String(Files.readAllBytes(Paths.get("src/test/resources/federalState.json")));
    JsonNode jsonNode = objectMapper.readTree(content);
    this.mockFederalStateDataList =
        objectMapper.readValue(
            jsonNode.asText(),
            objectMapper
                .getTypeFactory()
                .constructCollectionType(List.class, PopulationData.class));

    String contentR = new String(Files.readAllBytes(Paths.get("src/test/resources/region.json")));
    JsonNode jsonNodeR = objectMapper.readTree(contentR);
    this.mockRegionDataList =
        objectMapper.readValue(
            jsonNodeR.asText(),
            objectMapper
                .getTypeFactory()
                .constructCollectionType(List.class, PopulationData.class));
  }

  private FederalStateData createFSData(PopulationData populationData) {
    Optional<FederalStatesExtension.FederalStatesExtensionEnum> foundEnum =
        Arrays.stream(FederalStatesExtension.FederalStatesExtensionEnum.values())
            .filter(e -> e.code.equals(populationData.metaData.get(0).getCodigo()))
            .findAny();
    com.udima.tfg.model.FederalStateData federalStateData =
        new com.udima.tfg.model.FederalStateData(populationData);
    federalStateData.setFederalStatesExtensionEnum(foundEnum.orElse(null));
    federalStateData.setExtension(foundEnum.isPresent() ? foundEnum.get().extension : 0.0);
    federalStateData.setDisplayName(foundEnum.get().displayName);
    return federalStateData;
  }

  private RegionData createRData(PopulationData populationData) {
    Optional<RegionExtension.RegionExtensionEnum> foundEnum =
        Arrays.stream(RegionExtension.RegionExtensionEnum.values())
            .filter(e -> e.code.equals(populationData.metaData.get(0).getCodigo()))
            .findAny();
    com.udima.tfg.model.RegionData regionData = new com.udima.tfg.model.RegionData(populationData);
    regionData.setRegionExtensionEnum(foundEnum.orElse(null));
    regionData.setExtension(
        foundEnum.map(regionExtensionEnum -> regionExtensionEnum.extension).orElse(0.0));
    regionData.setDisplayName(foundEnum.get().displayName);
    return regionData;
  }
}
