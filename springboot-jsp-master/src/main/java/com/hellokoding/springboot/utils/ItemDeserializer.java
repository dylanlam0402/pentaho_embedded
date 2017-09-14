package com.hellokoding.springboot.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hellokoding.springboot.domain.Children;
import com.hellokoding.springboot.domain.File;

import java.io.IOException;
import java.util.List;

/**
 * Created by congle on 9/13/2017.
 */
public class ItemDeserializer extends StdDeserializer<Children> {


    protected ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Children deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
//        List<Children> listChildren = node.get("children");
//        List<File> listFile = node.get("file");

        return null;
    }
}
