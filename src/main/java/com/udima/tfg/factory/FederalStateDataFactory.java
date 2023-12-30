package com.udima.tfg.factory;

import com.udima.tfg.core.FederalStatesExtension;
import com.udima.tfg.model.Data;
import com.udima.tfg.model.FederalStateData;
import com.udima.tfg.model.PopulationData;
import java.util.Arrays;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * The FederalStateDataFactory class is responsible for creating FederalStateData objects based on PopulationData objects.
 */
@Component
public class FederalStateDataFactory
    implements PopulationDataFactory<com.udima.tfg.model.FederalStateData> {
  @Override
  public com.udima.tfg.model.FederalStateData createData(PopulationData populationData) {
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
}
