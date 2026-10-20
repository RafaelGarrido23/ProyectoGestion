package dao.fjson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.ProfesorDAO;
import modelo.Departamento;
import modelo.Profesor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProfesorDAOFJS implements ProfesorDAO {

    private final String ruta;

    public ProfesorDAOFJS(String ruta) { this.ruta = ruta; }

    @Override
    public ArrayList<Profesor> leerFichero(){

        try (FileReader flujoDatos =
                     new FileReader(ruta)){

            Gson gson = new Gson();
            Type tipoLista =
                    new TypeToken<ArrayList<Departamento>>(){}.getType();
            ArrayList<Profesor> profesores =
                    gson.fromJson(flujoDatos, tipoLista);

            return profesores;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer los profesores", e);
        }
    }

    @Override
    public void escribirFichero(ArrayList<Profesor> profesores) {

        try {
            Gson gson = new Gson();
            FileWriter flujoDatos = new FileWriter(ruta);
            gson.toJson(profesores, flujoDatos);
            flujoDatos.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir las reservas", e);
        }
    }


}
