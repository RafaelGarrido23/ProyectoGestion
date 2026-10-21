package dao.fjson.adaptadoresJSON;

import com.google.gson.*;
import modelo.Espacio;
import modelo.Profesor;
import modelo.Reserva;

import java.lang.reflect.Type;

public class DeserializadorReserva implements JsonDeserializer<Reserva> {
    @Override
    public Reserva deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Reserva reserva = new Reserva();

        // Extraemos los IDs como String desde el JSON
        String idProfesorStr = jsonObject.get("idProfesor").getAsString();
        String idEspacioStr = jsonObject.get("idEspacio").getAsString();

        // Construimos los objetos intermedios solo con el ID
        reserva.setIdProfesor(new Profesor(idProfesorStr));
        reserva.setIdEspacio(new Espacio(idEspacioStr));

        // Si tienes otros campos en Reserva (ej. fecha, hora), los parseas aquí:
        // reserva.setFecha(jsonObject.get("fecha").getAsString());

        return reserva;
    }
}
