package com.udima.tfg.core;

public class RegionExtension {

  public enum RegionExtensionEnum {
    BURGOS(14195.92, "09", "07", "Burgos"),
    CORDOBA(13726.63, "14", "01", "Córdoba"),
    AVILA(8047.23, "05", "07", "Ávila"),
    LLEIDA(12150.79, "25", "09", "Lérida"),
    HUELVA(10090.49, "21", "01", "Huelva"),
    SEGOVIA(6943.31, "40", "07", "Segovia"),
    BADAJOZ(21646.93, "06", "11", "Badajoz"),
    LEON(13377.17, "24", "07", "León"),
    GIPUZKOA(1844.71, "20", "16", "Guipúzcoa"),
    ALICANTE(5799, "03", "10", "Alicante"),
    ALAVA(3044.22, "01", "16", "Álava"),
    MALAGA(7285.12, "29", "01", "Málaga"),
    LUGO(9880.54, "27", "12", "Lugo"),
    VALENCIA(10957.71, "46", "10", "Valencia"),
    BALEARES(4992, "07", null, "Baleares"),
    OURENSE(6978.71, "32", "12", "Orense"),
    PONTEVEDRA(4391.32, "36", "12", "Pontevedra"),
    ZAMORA(10614.71, "49", "07", "Zamora"),
    CUENCA(17193.49, "16", "08", "Cuenca"),
    SORIA(10318.05, "42", "07", "Soria"),
    LAS_PALMAS(4066, "35", "05", "Las Palmas"),
    ALBACETE(14863.1, "02", "08", "Albacete"),
    VALLADOLID(8170.11, "47", "07", "Valladolid"),
    SANTA_CRUZ_DE_TENERIFE(3381, "38", "05", "S/C de Tenerife"),
    GIRONA(3864.96, "17", "09", "Gerona"),
    TARRAGONA(6490.35, "43", "09", "Tarragona"),
    SEVILLA(14062.5, "41", "01", "Sevilla"),
    TERUEL(14817.94, "44", "02", "Teruel"),
    ALMERIA(8777.57, "04", "01", "Almería"),
    SALAMANCA(12321.37, "37", "07", "Salamanca"),
    PALENCIA(8433.79, "34", "07", "Palencia"),
    HUESCA(15148.3, "22", "02", "Huesca"),
    TOLEDO(15346.36, "45", "08", "Toledo"),
    JAEN(13480.38, "23", "01", "Jaén"),
    BIZKAIA(2165.46, "48", "16", "Vizcaya"),
    CACERES(19960.83, "10", "11", "Cáceres"),
    CADIZ(7323.49, "11", "01", "Cádiz"),
    LA_CORUNIA(7902.79, "15", "12", "La Coruña"),
    CIUDAD_REAL(19741.15, "13", "08", "C. Real"),
    ZARAGOZA(17424.34, "50", "02", "Zaragoza"),
    CASTELLON(6465.37, "12", "10", "Castellón"),
    GRANADA(12529.44, "18", "01", "Granada"),
    GUADALAJARA(12192.32, "19", "08", "Guadalajara"),
    BARCELONA(7690.5, "08", "09", "Barcelona"),
    ASTURIAS(10604, "33", null, "Asturias"),
    CANTABRIA(5321, "39", null, "Cantabria"),
    MADRID(8002.11, "28", null, "Madrid"),
    MURCIA(11317.29, "30", null, "Murcia"),
    CEUTA(18.5, "51", null, "Ceuta"),
    MELILLA(12.3, "52", null, "Melilla"),
    NAVARRA(10506.37, "31", null, "Navarra"),
    LA_RIOJA(5045, "26", null, "La Rioja");

    public final double extension;
    public final String code;
    public final String codeFederalState;
    public final String displayName;

    RegionExtensionEnum(
        double extension, String code, String codeFederalState, String displayName) {
      this.extension = extension;
      this.code = code;
      this.codeFederalState = codeFederalState;
      this.displayName = displayName;
    }
  }
}
