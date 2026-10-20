package dao.fjson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.DepartamentoDAO;
import modelo.Departamento;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DepartamentoDAOFJS implements DepartamentoDAO {

    private final String ruta;

    public DepartamentoDAOFJS(String ruta) { this.ruta = ruta; }

    @Override
    public ArrayList<Departamento> leerFichero(){

        try (FileReader flujoDatos =
                new FileReader(ruta)){

            Gson gson = new Gson();
            Type tipoLista =
                new TypeToken<ArrayList<Departamento>>(){}.getType();
            ArrayList<Departamento> departamentos =
                gson.fromJson(flujoDatos, tipoLista);

            return departamentos;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer los departamentos", e);
        }
    }

    @Override
    public void escribirFichero(ArrayList<Departamento> departamentos) {

        try {
            Gson gson = new Gson();
            FileWriter flujoDatos = new FileWriter(ruta);
            gson.toJson(departamentos, flujoDatos);
            flujoDatos.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir las reservas", e);
        }
    }

}
