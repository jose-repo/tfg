package com.udima.tfg.factory;

import com.udima.tfg.core.RegionExtension;
import com.udima.tfg.model.PopulationData;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class RegionDataFactory<RegionData>
    implements PopulationDataFactory<com.udima.tfg.model.RegionData> {
  @Override
  public com.udima.tfg.model.RegionData createData(PopulationData populationData) {
    Optional<RegionExtension.RegionExtensionEnum> foundEnum =
        Arrays.stream(RegionExtension.RegionExtensionEnum.values())
            .filter(e -> e.code.equals(populationData.metaData.get(0).getCodigo()))
            .findAny();
    com.udima.tfg.model.RegionData regionData = new com.udima.tfg.model.RegionData(populationData);
    regionData.setRegionExtensionEnum(foundEnum.orElse(null));
    regionData.setExtension(foundEnum.map(regionExtensionEnum -> regionExtensionEnum.extension).orElse(0.0));
    return regionData;
  }
}
