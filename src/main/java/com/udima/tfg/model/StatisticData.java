package com.udima.tfg.model;

import com.udima.tfg.core.RiskLevel;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StatisticData {
    public List<FederalStateData> federalStateDataList;

    public RiskLevel.RiskLevelEnum depopulationRiskLevel;
}
