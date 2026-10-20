package dao;

import modelo.Departamento;

import java.util.ArrayList;

public interface DepartamentoDAO {

    public ArrayList<Departamento> leerFichero();

    public void escribirFichero(ArrayList<Departamento> departamentos);
}
