package dao.fjson.adaptadoresJSON;

import com.google.gson.*;
import modelo.Departamento;
import modelo.Profesor;

import java.lang.reflect.Type;

public class SerializadorProfesor implements JsonSerializer<Profesor> {

    @Override
    public JsonElement serialize(Profesor src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getIdProfesor());
    }
}
