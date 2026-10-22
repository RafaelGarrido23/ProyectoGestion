package dao.fjson.adaptadoresJSON;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import modelo.Espacio;

import java.lang.reflect.Type;

public class SerializadorEspacio implements JsonSerializer<Espacio> {

    @Override
    public JsonElement serialize(Espacio src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getIdEspacio());
    }
}
