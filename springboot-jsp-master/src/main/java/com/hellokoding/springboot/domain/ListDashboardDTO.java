package com.hellokoding.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by kietlam on 9/11/2017.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ListDashboardDTO {
    @JsonProperty("repositoryFileDto")
    List<Dashboard> dashboardList;

}
