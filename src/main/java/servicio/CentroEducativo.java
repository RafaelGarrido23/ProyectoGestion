package servicio;

import dao.DepartamentoDAO;
import dao.EspacioDAO;
import dao.ProfesorDAO;
import dao.ReservaDAO;
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
import vista.MainFX;

import java.io.IOException;
import java.util.ArrayList;

public class CentroEducativo {

    private DepartamentoDAO departamentoDAO;
    private ProfesorDAO profesorDAO;
    private EspacioDAO espacioDAO;
    private ReservaDAO reservaDAO;

    private ArrayList<Departamento> departamentos = new ArrayList<>();
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private ArrayList<Espacio> espacios = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();


    public CentroEducativo(DepartamentoDAOFB departamentoDAOFB, ProfesorDAOFB profesorDAOFB, EspacioDAOFB espacioDAOFB, ReservaDAOFB reservaDAOFB) throws IOException {
        this.departamentoDAO = departamentoDAOFB;
        this.profesorDAO = profesorDAOFB;
        this.espacioDAO = espacioDAOFB;
        this.reservaDAO = reservaDAOFB;
        leerTodo();
    }

    public CentroEducativo(DepartamentoDAOFJS departamentoDAOFJS, ProfesorDAOFJS profesorDAOFJS, EspacioDAOFJS espacioDAOFJS, ReservaDAOFJS reservaDAOFJS) throws IOException {
        this.departamentoDAO = departamentoDAOFJS;
        this.profesorDAO = profesorDAOFJS;
        this.espacioDAO = espacioDAOFJS;
        this.reservaDAO = reservaDAOFJS;
        leerTodo();
    }

    /*

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

    public void guardarTodo() throws IOException {

        departamentoDAOCSV.escribirFichero(departamentos);
        departamentoDAOFB.escribirFichero(departamentos);
        departamentoDAOFJS.escribirFichero(departamentos);

        profesorDAOCSV.escribirFichero(profesores);
        profesorDAOFB.escribirFichero(profesores);
        profesorDAOFJS.escribirFichero(profesores);

        espacioDAOCSV.escribirFichero(espacios);
        espacioDAOFB.escribirFichero(espacios);
        espacioDAOFJS.escribirFichero(espacios);

        reservaDAOCSV.escribirFichero(reservas);
        reservaDAOFB.escribirFichero(reservas);
        reservaDAOFJS.escribirFichero(reservas);
    }

     */

    public void leerTodo() {
        departamentos = departamentoDAO.leerFichero();
        profesores = profesorDAO.leerFichero();
        espacios = espacioDAO.leerFichero();
        reservas = reservaDAO.leerFichero();
    }

    public void guardarTodo() throws IOException{
        departamentoDAO.escribirFichero(departamentos);
        profesorDAO.escribirFichero(profesores);
        espacioDAO.escribirFichero(espacios);
        reservaDAO.escribirFichero(reservas);
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
