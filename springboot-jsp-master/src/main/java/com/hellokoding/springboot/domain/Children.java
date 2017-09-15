package com.hellokoding.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by congle on 9/13/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)

public class Children {

    @JsonProperty("file")
    File file;

    @JsonProperty("children")
    List<Children> childrenList;

    @JsonIgnore
    public  static List<File> allFilesAndFolders = new ArrayList<>();


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    public static void printTree(Children children){
        if(children.getFile()!=null)
        {
            allFilesAndFolders.add(children.getFile());
        }
        if(children.getChildrenList()!=null) {
            for (Children each : children.getChildrenList()) {
                if (each != null) {
                    printTree(each);
                }
            }
        }
    }
}
