package com.hellokoding.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kietlam on 9/11/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Dashboard {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("path")
    private String path;

    @JsonProperty("createdDate")
    private String createDate;



}
