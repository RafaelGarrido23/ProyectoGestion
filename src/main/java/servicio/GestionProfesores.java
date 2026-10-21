package servicio;

import dao.ProfesorDAO;
import modelo.Profesor;

import java.util.ArrayList;

public class GestionProfesores {

    private final ProfesorDAO profesorDAO;
    private final ArrayList<Profesor> profesores;

    public GestionProfesores(ProfesorDAO profesorDAO, ArrayList<Profesor> profesores) {
        this.profesorDAO = profesorDAO;
        this.profesores = profesorDAO.leerFichero();
    }

    public void altaProfesor(Profesor profesor){
        validarProfesor(profesor);

        if ((buscarPorId(profesor.getIdProfesor()) != null)){
            throw new IllegalArgumentException("Ya existe un profesor con ese ID");
        }

        profesores.add(profesor);
    }

    public void modificarProfesor(Profesor modificado){
        validarProfesor(modificado);

        Profesor profesor = buscarPorId(modificado.getIdProfesor());

        if (profesor == null){
            throw new IllegalArgumentException("El profesor no existe");
        }

        profesor.setNombre(profesor.getNombre());
        profesor.setApellidos(profesor.getApellidos());
        profesor.setEmail(profesor.getEmail());
        profesor.setIdDepartamento(profesor.getIdDepartamento());
    }

    public void eliminarProfesor(String id){
        Profesor profesor = buscarPorId(id);

        if (profesor == null){
            throw new IllegalArgumentException("El profesor no existe");
        }

        profesores.remove(profesor);
    }

    public ArrayList<Profesor> consultarProfesores(){
        return new ArrayList<>(profesores);
    }

    public Profesor consultarProfesor(String id){
        Profesor profesor = buscarPorId(id);

        if (profesor == null){
            throw new IllegalArgumentException("El profesor no existe");
        }

        return profesor;
    }

    public ArrayList<Profesor> consultarPorDepartamento(String id){
        ArrayList<Profesor> resultado = new ArrayList<>();

        for (Profesor profesor : profesores){
            if (profesor.getIdDepartamento() != null &&
                    profesor.getIdDepartamento().getIdDepartamento() != null &&
                        profesor.getIdDepartamento().getIdDepartamento().equals(id)){
                resultado.add(profesor);
            }
        }
        return resultado;
    }

    private void validarProfesor(Profesor profesor) {
        if (profesor == null){ throw new IllegalArgumentException("El profesor puede ser nulo"); }
        if (profesor.getIdProfesor() == null || profesor.getIdProfesor().trim().isEmpty()){ throw new IllegalArgumentException("El ID es obligatorio"); }
        if (profesor.getNombre() == null || profesor.getNombre().trim().isEmpty()){ throw new IllegalArgumentException("El nombre es obligatorio"); }
        if (profesor.getApellidos() == null || profesor.getApellidos().trim().isEmpty()){ throw new IllegalArgumentException("Los apellidos son obligatorios"); }
        if (profesor.getEmail() == null || profesor.getEmail().trim().isEmpty()){ throw new IllegalArgumentException("El email es obligatorio"); }
        if (profesor.getIdDepartamento() == null){ throw new IllegalArgumentException("El profesor debe pertenecer a un departamento"); }
    }

    private Profesor buscarPorId(String id){
        for (Profesor profesor : profesores){
            if (profesor.getIdProfesor().equals(id)){
                return profesor;
            }
        }
        return null;
    }
}
