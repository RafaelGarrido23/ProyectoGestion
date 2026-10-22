package dao.fjson.adaptadoresJSON;

import com.google.gson.*;
import modelo.Espacio;
import modelo.EstadoReserva;
import modelo.Profesor;
import modelo.Reserva;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;

public class DeserializadorReserva implements JsonDeserializer<Reserva> {

    @Override
    public Reserva deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        Reserva reserva = new Reserva();

        reserva.setIdReserva(jsonObject.get("idReserva").getAsString());
        reserva.setFecha(LocalDate.parse(jsonObject.get("fecha").getAsString()));
        reserva.setHoraInicio(LocalTime.parse(jsonObject.get("horaInicio").getAsString()));
        reserva.setHoraFin(LocalTime.parse(jsonObject.get("horaFin").getAsString()));
        reserva.setEstado(EstadoReserva.valueOf(jsonObject.get("estado").getAsString()));
        reserva.setNumAsistentes(Integer.parseInt(jsonObject.get("numAsistentes").getAsString()));

        if (jsonObject.has("idProfesor")) {
            String idProfesorStr = jsonObject.get("idProfesor").getAsString();
            reserva.setIdProfesor(new Profesor(idProfesorStr));
        }

        if (jsonObject.has("idEspacio")) {
            String idEspacioStr = jsonObject.get("idEspacio").getAsString();
            reserva.setIdEspacio(new Espacio(idEspacioStr));
        }

        return reserva;
    }
}
