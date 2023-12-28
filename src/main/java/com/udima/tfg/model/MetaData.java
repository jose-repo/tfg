package com.udima.tfg.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Variable",
        "Nombre",
        "Codigo"
})
@Getter
@Setter
public class MetaData {

    @JsonProperty("Id")
    public int id;
    @JsonProperty("Variable")
    public Variable variable;
    @JsonProperty("Nombre")
    public String nombre;
    @JsonProperty("Codigo")
    public String codigo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}