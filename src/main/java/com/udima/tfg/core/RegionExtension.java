package com.udima.tfg.core;

public class RegionExtension {

    public enum RegionExtensionEnum {

        BURGOS(14195.92, "09"),
        CORDOBA(13726.63, "14"),
        AVILA(8047.23, "05"),
        LLEIDA(12150.79, "25"),
        HUELVA(10090.49, "21"),
        SEGOVIA(6943.31, "40"),
        BADAJOZ(21646.93, "06"),
        LEON(13377.17, "24"),
        GIPUZKOA(1844.71, "20"),
        ALICANTE(5799, "03"),
        ALAVA(3044.22, "01"),
        MALAGA(7285.12, "29"),
        LUGO(9880.54, "27"),
        VALENCIA(10957.71, "46"),
        BALEARES(4992, "07"),
        OURENSE(6978.71, "32"),
        PONTEVEDRA(4391.32, "36"),
        ZAMORA(10614.71, "49"),
        CUENCA(17193.49, "16"),
        SORIA(10318.05, "42"),
        LAS_PALMAS(4066, "35"),
        ALBACETE(14863.1, "02"),
        VALLADOLID(8170.11, "47"),
        SANTA_CRUZ_DE_TENERIFE(3381, "38"),
        GIRONA(3864.96, "17"),
        TARRAGONA(6490.35, "43"),
        SEVILLA(14062.5, "41"),
        TERUEL(14817.94, "44"),
        ALMERIA(8777.57, "04"),
        SALAMANCA(12321.37, "37"),
        PALENCIA(8433.79, "34"),
        HUESCA(15148.3, "22"),
        TOLEDO(15346.36, "45"),
        JAEN(13480.38, "23"),
        BIZKAIA(2165.46, "48"),
        CACERES(19960.83, "10"),
        CADIZ(7323.49, "11"),
        LA_CORUNIA(7902.79, "15"),
        CIUDAD_REAL(19741.15, "13"),
        ZARAGOZA(17424.34, "50"),
        CASTELLON(6465.37, "12"),
        GRANADA(12529.44, "18"),
        GUADALAJARA(12192.32, "19"),
        BARCELONA(7690.5, "08"),
        ASTURIAS(10604, "33"),
        CANTABRIA(5321, "39"),
        MADRID(8002.11, "28"),
        MURCIA(11317.29, "30"),
        CEUTA(18.5, "51"),
        MELILLA(12.3, "52"),
        NAVARRA(10506.37, "31"),
        LA_RIOJA(5045, "26");

        public final double extension;
        public final String code;

        RegionExtensionEnum(double extension, String code) {
            this.extension = extension;
            this.code = code;
        }
    }
}
