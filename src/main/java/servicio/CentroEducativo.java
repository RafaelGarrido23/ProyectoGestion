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


    /* Constructor CSV. Hace falta implementar métodos leerFichero y escribirFichero en las clases DAOCSV para que funcione.

    public CentroEducativo(DepartamentoDAOCSV departamentoDAOCSV, ProfesorDAOCSV profesorDAOCSV, EspacioDAOCSV espacioDAOCSV, ReservaDAOCSV reservaDAOCSV) throws IOException {
        this.departamentoDAO = departamentoDAOCSV;
        this.profesorDAO = profesorDAOCSV;
        this.espacioDAO = espacioDAOCSV;
        this.reservaDAO = reservaDAOCSV;
        leerTodo();
    }

    */

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

    public void leerTodo() {
        departamentos = departamentoDAO.leerFichero();
        profesores = profesorDAO.leerFichero();
        espacios = espacioDAO.leerFichero();
        reservas = reservaDAO.leerFichero();

        asociarAProfesores();
        asociarAReservas();
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

    private Departamento buscarDepartamentoPorId(String id){
        for (Departamento departamento : departamentos){
            if (departamento.getIdDepartamento().equals(id)){
                return departamento;
            }
        }
        return null;
    }

    private void asociarAProfesores() {

        for (Profesor profesor : profesores) {
            Departamento departamento = buscarDepartamentoPorId(
                profesor.getIdDepartamento() == null
                        ? null
                        : profesor.getIdDepartamento().getIdDepartamento()
            );

            profesor.setIdDepartamento(departamento);
        }
    }

    private void asociarAReservas() {
        for (Reserva reserva : reservas) {

            if (reserva.getIdProfesor() != null) {
                String idProfesor = reserva.getIdProfesor().getIdProfesor();

                for (Profesor profesor : profesores) {
                    if (profesor.getIdProfesor().equals(idProfesor)) {
                        reserva.setIdProfesor(profesor);
                        break;
                    }
                }
            }

            if (reserva.getIdEspacio() != null) {
                String idEspacio = reserva.getIdEspacio().getIdEspacio();

                for (Espacio espacio : espacios) {
                    if (espacio.getIdEspacio().equals(idEspacio)) {
                        reserva.setIdEspacio(espacio);
                        break;
                    }
                }
            }
        }
    }
}
