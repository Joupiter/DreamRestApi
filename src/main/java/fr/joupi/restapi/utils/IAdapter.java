package fr.joupi.restapi.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public interface IAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {

    @Override
    default JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonObject();
    }

    @Override
    default T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return null;
    }

}
