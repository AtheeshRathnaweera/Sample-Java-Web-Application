package com.atheesh.samplejavawebapp.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

public class TimeAdapter implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
    }
}
