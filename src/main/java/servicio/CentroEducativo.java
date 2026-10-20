package servicio;

import dao.fcsv.DepartamentoDAOCSV;
import dao.fcsv.EspacioDAOCSV;
import dao.fcsv.ProfesorDAOCSV;
import dao.fcsv.ReservaDAOCSV;
import dao.fdat.DepartamentoDAOFB;
import dao.fdat.EspacioDAOFB;
import dao.fdat.ProfesorDAOFB;
import dao.fdat.ReservaDAOFB;
import dao.fjson.DepartamentoDAOFJS;
import dao.fjson.EspacioDAOFJS;
import dao.fjson.ProfesorDAOFJS;
import dao.fjson.ReservaDAOFJS;
import modelo.Departamento;
import modelo.Espacio;
import modelo.Profesor;
import modelo.Reserva;

import java.io.IOException;
import java.util.ArrayList;

public class CentroEducativo {

    ArrayList<Departamento> departamentos = new ArrayList<>();
    ArrayList<Profesor> profesores = new ArrayList<>();
    ArrayList<Espacio> espacios = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();

    DepartamentoDAOCSV departamentoDAOCSV = new DepartamentoDAOCSV();
    ProfesorDAOCSV profesorDAOCSV = new ProfesorDAOCSV();
    EspacioDAOCSV espacioDAOCSV = new EspacioDAOCSV();
    ReservaDAOCSV reservaDAOCSV = new ReservaDAOCSV();

    DepartamentoDAOFB departamentoDAOFB = new DepartamentoDAOFB("dat/departamento.dat");
    ProfesorDAOFB profesorDAOFB = new ProfesorDAOFB("dat/espacios.dat");
    EspacioDAOFB espacioDAOFB = new EspacioDAOFB("dat/profesores.dat");
    ReservaDAOFB reservaDAOFB = new ReservaDAOFB("dat/reserva.dat");

    DepartamentoDAOFJS departamentoDAOFJS = new DepartamentoDAOFJS("json/departamento.json");
    ProfesorDAOFJS profesorDAOFJS = new ProfesorDAOFJS("json/profesores.json");
    EspacioDAOFJS espacioDAOFJS = new EspacioDAOFJS("json/espacios.json");
    ReservaDAOFJS reservaDAOFJS = new ReservaDAOFJS("json/reserva.json");

    public CentroEducativo(DepartamentoDAOCSV departamentoDAOCSV, ProfesorDAOCSV profesorDAOCSV, EspacioDAOCSV espacioDAOCSV, ReservaDAOCSV reservaDAOCSV) throws IOException{
    }

    /*
    public void leerFicherosCSV(){

        departamentos = departamentoDAOCSV.leerFichero();
        profesores = profesorDAOCSV.leerFichero();
        espacios = espacioDAOCSV.leerFichero();
        reservas = reservaDAOCSV.leerFichero();
    }
    */

    public void leerFicherosDAT(){

        departamentos = departamentoDAOFB.leerFichero();
        profesores = profesorDAOFB.leerFichero();
        espacios = espacioDAOFB.leerFichero();
        reservas = reservaDAOFB.leerFichero();
    }

    public void leerFicherosJSON(){

        departamentos = departamentoDAOFJS.leerFichero();
        profesores = profesorDAOFJS.leerFichero();
        espacios = espacioDAOFJS.leerFichero();
        reservas = reservaDAOFJS.leerFichero();
    }

    public void guardarTodo() throws IOException {

        /*
        departamentoDAOCSV.escribirFichero(departamentos);
        profesorDAOCSV.escribirFichero(profesores);
        espacioDAOCSV.escribirFichero(espacios);
        reservaDAOCSV.escribirFichero(reservas);
        */

        departamentoDAOFB.escribirFichero(departamentos);
        profesorDAOFB.escribirFichero(profesores);
        espacioDAOFB.escribirFichero(espacios);
        reservaDAOFB.escribirFichero(reservas);

        departamentoDAOFJS.escribirFichero(departamentos);
        profesorDAOFJS.escribirFichero(profesores);
        espacioDAOFJS.escribirFichero(espacios);
        reservaDAOFJS.escribirFichero(reservas);
    }

    public ArrayList<Departamento> getDepartamentos(){
        return departamentos;
    }

    public ArrayList<Profesor> getProfesores(){
        return profesores;
    }

    public ArrayList<Espacio> getEspacios(){
        return espacios;
    }

    public ArrayList<Reserva> getReservas(){
        return reservas;
    }
}
