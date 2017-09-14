package com.hellokoding.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by congle on 9/13/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)

public class Children {
    @JsonProperty("children")
    List<Children> children;

    @JsonProperty("file")
    List<File> file;

    public Children(List<Children> childrenList, List<File> fileList) {

    }

    public List<Children> getChildren() {
        return children;
    }
    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    @JsonIgnore
    static List<List<File>> allFile = new ArrayList<>();

   

}
