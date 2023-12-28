package com.udima.tfg.core;

public class RegionExtension {

    public enum RegionExtensionEnum {

        BURGOS(14195.92, "09","07"),
        CORDOBA(13726.63, "14", "01"),
        AVILA(8047.23, "05", "07"),
        LLEIDA(12150.79, "25", "09"),
        HUELVA(10090.49, "21", "01"),
        SEGOVIA(6943.31, "40", "07"),
        BADAJOZ(21646.93, "06", "11"),
        LEON(13377.17, "24", "07"),
        GIPUZKOA(1844.71, "20", "16"),
        ALICANTE(5799, "03", "10"),
        ALAVA(3044.22, "01", "16"),
        MALAGA(7285.12, "29", "01"),
        LUGO(9880.54, "27", "12"),
        VALENCIA(10957.71, "46", "10"),
        BALEARES(4992, "07", "04"),
        OURENSE(6978.71, "32", "12"),
        PONTEVEDRA(4391.32, "36", "12"),
        ZAMORA(10614.71, "49", "07"),
        CUENCA(17193.49, "16", "08"),
        SORIA(10318.05, "42", "07"),
        LAS_PALMAS(4066, "35", "05"),
        ALBACETE(14863.1, "02", "08"),
        VALLADOLID(8170.11, "47", "07"),
        SANTA_CRUZ_DE_TENERIFE(3381, "38", "05"),
        GIRONA(3864.96, "17", "09"),
        TARRAGONA(6490.35, "43", "09"),
        SEVILLA(14062.5, "41", "01"),
        TERUEL(14817.94, "44", "02"),
        ALMERIA(8777.57, "04", "01"),
        SALAMANCA(12321.37, "37", "07"),
        PALENCIA(8433.79, "34", "07"),
        HUESCA(15148.3, "22", "02"),
        TOLEDO(15346.36, "45", "08"),
        JAEN(13480.38, "23", "01"),
        BIZKAIA(2165.46, "48", "16"),
        CACERES(19960.83, "10", "11"),
        CADIZ(7323.49, "11", "01"),
        LA_CORUNIA(7902.79, "15", "12"),
        CIUDAD_REAL(19741.15, "13", "08"),
        ZARAGOZA(17424.34, "50", "02"),
        CASTELLON(6465.37, "12", "10"),
        GRANADA(12529.44, "18", "01"),
        GUADALAJARA(12192.32, "19", "08"),
        BARCELONA(7690.5, "08", "09"),
        ASTURIAS(10604, "33", "03"),
        CANTABRIA(5321, "39", "06"),
        MADRID(8002.11, "28", "13"),
        MURCIA(11317.29, "30", "14"),
        CEUTA(18.5, "51", null),
        MELILLA(12.3, "52", null),
        NAVARRA(10506.37, "31", "15"),
        LA_RIOJA(5045, "26", "17");

        public final double extension;
        public final String code;
        public final String codeFederalState;

        RegionExtensionEnum(double extension, String code, String codeFederalState) {
            this.extension = extension;
            this.code = code;
            this.codeFederalState = codeFederalState;
        }
    }
}
