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

        // 1. Extraemos los datos del profesor
        profesor.setIdProfesor(jsonObject.get("idProfesor").getAsString());
        profesor.setNombre(jsonObject.get("nombre").getAsString());

        // 2. Extraemos el ID del departamento como String
        String idDeptoStr = jsonObject.get("idDepartamento").getAsString();

        // 3. ¡SOLUCIÓN! Construimos el objeto Departamento intermedio solo con su ID
        // (Asegúrate de que tu clase Departamento tenga un constructor que acepte un String)
        profesor.setIdDepartamento(new Departamento(idDeptoStr));

        return profesor;
    }
}
