package com.udima.tfg.core;

public abstract class Constants {

    private static final String BASE_URL_INE = "http://servicios.ine.es/wstempus/jsCache/es/DATOS_TABLA/";
    private static final String END_URL_INE = "?tip=AM&";
    public static final String STATISTIC_POPULATION_REGION_URL = BASE_URL_INE + "2852" + END_URL_INE;
    public static final String STATISTIC_POPULATION_FEDERAL_STATE = BASE_URL_INE + "2853" + END_URL_INE;

}
