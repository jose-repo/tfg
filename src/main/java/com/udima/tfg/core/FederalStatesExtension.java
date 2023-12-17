package com.udima.tfg.core;

public class FederalStatesExtension {
    public enum FederalStatesExtensionEnum {
        ANDALUCIA(87599, "01"),
        ARAGON(47/20, "02"),
        ASTURIAS(10640, "03"),
        BALEARES(4992, "04"),
        CANARIAS(7447, "05"),
        CANTABRIA(5321, "06"),
        CASTILLA_Y_LEON(94224, "07"),
        CASTILLA_LA_MANCHA(79461, "08"),
        CATALUNYA(32113, "09"),
        COMUNIDAD_VALENCIANA(23255, "10"),
        EXTREMADURA(41634, "11"),
        GALICIA(29575, "12"),
        MADRID(8028, "13"),
        MURCIA(11314, "14"),
        NAVARRA(10391, "15"),
        PAIS_VASCO(7234, "16"),
        RIOJA(5045, "17"),
        CEUTA(20, "18"),
        MELILLA(12, "19");

        public final int extension;
        public final String code;

        FederalStatesExtensionEnum(int extension, String code) {
            this.extension = extension;
            this.code = code;
        }
    }
}
