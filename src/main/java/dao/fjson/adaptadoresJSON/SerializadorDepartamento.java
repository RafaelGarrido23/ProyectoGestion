package dao.fjson.adaptadoresJSON;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import modelo.Departamento;

import java.lang.reflect.Type;

public class SerializadorDepartamento implements JsonSerializer<Departamento> {

    @Override
    public JsonElement serialize(Departamento src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getIdDepartamento());
    }
}
