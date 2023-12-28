package com.udima.tfg.core;

public class RiskLevel {
    public enum RiskLevelEnum {

        LOW("Bajo", 0),
        MIDDLE("Medio", 1),
        HIGH("Alto", 2),
        EXTREM("Muy alto", 3);

        public final String risk;
        public final int level;

        RiskLevelEnum(String risk, int level) {
            this.risk = risk;
            this.level = level;
        }
    }
}
