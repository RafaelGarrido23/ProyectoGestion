package dao.fjson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.ReservaDAO;
import modelo.Departamento;
import modelo.Profesor;
import modelo.Reserva;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReservaDAOFJS implements ReservaDAO {

    private final String ruta;

    public ReservaDAOFJS(String ruta) { this.ruta = ruta; }

    @Override
    public ArrayList<Reserva> leerFichero(){

        try (FileReader flujoDatos = new FileReader(ruta)){

            Gson gson = new Gson();
            Type tipoLista =
                new TypeToken<ArrayList<Reserva>>(){}.getType();
            ArrayList<Reserva> reservas =
                gson.fromJson(flujoDatos, tipoLista);

            return reservas;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer las reservas", e);
        }
    }

    @Override
    public void escribirFichero(ArrayList<Reserva> reservas) {

        try {
            Gson gson = new Gson();
            FileWriter flujoDatos = new FileWriter(ruta);
            gson.toJson(reservas, flujoDatos);
            flujoDatos.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir las reservas", e);
        }
    }
}
