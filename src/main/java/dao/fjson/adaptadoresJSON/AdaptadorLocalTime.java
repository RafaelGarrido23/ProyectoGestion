package dao.fjson.adaptadoresJSON;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AdaptadorLocalTime implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {

    private static final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalTime.parse(json.getAsString(), formateador);
    }

    @Override
    public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formateador));
    }
}
