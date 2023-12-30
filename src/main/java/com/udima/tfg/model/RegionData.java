package com.udima.tfg.model;

import com.udima.tfg.core.RegionExtension;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionData extends PopulationData {

  public RegionData(PopulationData populationData) {
    super(populationData.cod, populationData.nombre, populationData.t3Unidad, populationData.t3Escala, populationData.metaData, populationData.data, populationData.getAdditionalProperties());
  }

  public double extension;
  public String displayName;
  public RegionExtension.RegionExtensionEnum regionExtensionEnum;
  public double depopulationRiskLevel;
}
