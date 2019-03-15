package com.schauersberger.data.cache.serializer;

import com.google.gson.Gson;

import javax.inject.Inject;

public class Serializer {
    private final Gson gson = new Gson();

    @Inject
    Serializer() {}

    /**
     * Serialize an object to Json.
     *
     * @param object to serialize.
     */
    public String serialize(Object object, Class clazz) {
        return gson.toJson(object, clazz);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param string A json string to deserialize.
     */
    public <T> T deserialize(String string, Class<T> clazz) {
        return gson.fromJson(string, clazz);
    }
}

