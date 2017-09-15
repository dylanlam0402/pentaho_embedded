package com.hellokoding.springboot.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import com.fasterxml.jackson.databind.type.TypeFactory;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by congle on 9/14/2017.
 */
public class ChildrenDeserializer extends StdDeserializer<Children> {
    public ChildrenDeserializer() {
        this(null);
    }

    public ChildrenDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Children deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
//        JsonNode file = node.get("file");
//        JsonNode child= node.get("children");
//        ObjectMapper objectMapper = new ObjectMapper();
//        if (!file.isArray()){
//            File fileAttribute = objectMapper.readValue(file.toString(),File.class);
//            List<File> list = new ArrayList<>();
//            List<Children> childrenList = objectMapper.readValue(child.toString(), new TypeReference<List<Children>>(){});
//            list.add(fileAttribute);
//            return new Children(childrenList,list);
//
//        }
//        List<Children> childrenList = objectMapper.readValue(child.toString(), new TypeReference<List<Children>>(){});
//        List<File> fileList = objectMapper.readValue(file.toString(), new TypeReference<List<Children>>(){});
//
//        return new Children(childrenList,fileList);
        //  }
        return null;
    }
}
