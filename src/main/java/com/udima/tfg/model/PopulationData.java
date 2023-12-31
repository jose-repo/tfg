package com.udima.tfg.model;

import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "COD",
        "Nombre",
        "T3_Unidad",
        "T3_Escala",
        "MetaData",
        "Data"
})
@Getter
@Setter
@AllArgsConstructor
public class PopulationData {

    public PopulationData() {

    }

    @JsonProperty("COD")
    public String cod;
    @JsonProperty("Nombre")
    public String nombre;
    @JsonProperty("T3_Unidad")
    public String t3Unidad;
    @JsonProperty("T3_Escala")
    public String t3Escala;
    @JsonProperty("MetaData")
    public List<MetaData> metaData;
    @JsonProperty("Data")
    public List<Data> data;
    @JsonIgnore
    private Map<String, Object> additionalProperties;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
