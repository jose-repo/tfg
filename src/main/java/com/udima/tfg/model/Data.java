package com.udima.tfg.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Fecha",
        "T3_TipoDato",
        "T3_Periodo",
        "Anyo",
        "Valor"
})
@Getter
@Setter
public class Data {

    @JsonProperty("Fecha")
    public String fecha;
    @JsonProperty("T3_TipoDato")
    public String t3TipoDato;
    @JsonProperty("T3_Periodo")
    public String t3Periodo;
    @JsonProperty("Anyo")
    public int anyo;
    @JsonProperty("Valor")
    public int valor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();
    public int riskLevel;
    public double populationDensity;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}