package com.udima.tfg.model;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Cod_IOE",
        "Nombre",
        "Codigo",
        "Url"
})
public class OperacionesDisponibles {

    @JsonProperty("Id")
    private int id;
    @JsonProperty("Cod_IOE")
    private String codIOE;
    @JsonProperty("Nombre")
    private String nombre;
    @JsonProperty("Codigo")
    private String codigo;
    @JsonProperty("Url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("Id")
    public int getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("Cod_IOE")
    public String getCodIOE() {
        return codIOE;
    }

    @JsonProperty("Cod_IOE")
    public void setCodIOE(String codIOE) {
        this.codIOE = codIOE;
    }

    @JsonProperty("Nombre")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("Nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("Codigo")
    public String getCodigo() {
        return codigo;
    }

    @JsonProperty("Codigo")
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @JsonProperty("Url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("Url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}