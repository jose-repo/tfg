package com.udima.tfg.core;

public class FederalStatesExtension {
    public enum FederalStatesExtensionEnum {
        ANDALUCIA(87599, "01", "Andalucía"),
        ARAGON(47720, "02", "Aragón"),
        ASTURIAS(10640, "03", "Asturias"),
        BALEARES(4992, "04", "Baleares"),
        CANARIAS(7447, "05", "Canarias"),
        CANTABRIA(5321, "06", "Cantabria"),
        CASTILLA_Y_LEON(94224, "07", "Castilla-León"),
        CASTILLA_LA_MANCHA(79461, "08", "Castilla-La Mancha"),
        CATALUNYA(32113, "09", "Cataluña"),
        EXTREMADURA(41634, "11", "Extremadura"),
        GALICIA(29575, "12", "Galicia"),
        MADRID(8028, "13", "C. Madrid"),
        MURCIA(11314, "14", "R. Murcia"),
        NAVARRA(10391, "15", "Navarra"),
        PAIS_VASCO(7234, "16", "País Vasco"),
        RIOJA(5045, "17", "La Rioja"),
        COMUNIDAD_VALENCIANA(23255, "10", "C. Valenciana"),
        CEUTA(20, "18", "Ceuta"),
        MELILLA(12, "19", "Melilla");

        public final int extension;
        public final String code;
        public final String displayName;

        FederalStatesExtensionEnum(int extension, String code, String displayName) {
            this.extension = extension;
            this.code = code;
            this.displayName = displayName;
        }
    }
}
