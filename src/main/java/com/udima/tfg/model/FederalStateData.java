package com.udima.tfg.model;

import com.udima.tfg.core.FederalStatesExtension;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FederalStateData extends PopulationData {
    public FederalStateData(PopulationData populationData) {
        super(populationData.cod, populationData.nombre, populationData.t3Unidad, populationData.t3Escala, populationData.metaData, populationData.data, populationData.getAdditionalProperties());
    }
    public FederalStateData() {}
    
    public double extension;
    public String displayName;
    public FederalStatesExtension.FederalStatesExtensionEnum federalStatesExtensionEnum;
    public List<RegionData> regionDataList;
    public double depopulationRiskLevel;
}
