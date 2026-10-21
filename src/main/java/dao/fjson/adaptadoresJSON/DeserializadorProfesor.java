package dao.fjson.adaptadoresJSON;

import com.google.gson.*;
import modelo.Departamento;
import modelo.Profesor;

import java.lang.reflect.Type;

public class DeserializadorProfesor implements JsonDeserializer<Profesor> {
    @Override
    public Profesor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Profesor profesor = new Profesor();

        // 1. Mapear TODOS los atributos básicos de Profesor
        profesor.setIdProfesor(jsonObject.get("idProfesor").getAsString());
        profesor.setNombre(jsonObject.get("nombre").getAsString());
        profesor.setApellidos(jsonObject.get("apellidos").getAsString());
        profesor.setEmail(jsonObject.get("email").getAsString());

        // 2. Mapear el Departamento (creando el objeto intermedio con su ID)
        if (jsonObject.has("idDepartamento")) {
            String idDeptoStr = jsonObject.get("idDepartamento").getAsString();
            profesor.setIdDepartamento(new Departamento(idDeptoStr));
        }

        return profesor;
    }
}
