package servicio;

import dao.DepartamentoDAO;
import modelo.Departamento;

import java.util.ArrayList;

public class GestionDepartamentos {

    private final DepartamentoDAO departamentoDAO;
    private final ArrayList<Departamento> departamentos;

    public GestionDepartamentos(DepartamentoDAO departamentoDAO, ArrayList<Departamento> departamentos) {
        this.departamentoDAO = departamentoDAO;
        this.departamentos = departamentoDAO.leerFichero();
    }

    public void altaDepartamento(Departamento departamento){
        validarDepartamento(departamento);

        if ((buscarPorId(departamento.getIdDepartamento()) != null)){
            throw new IllegalArgumentException("Ya existe un departamento con ese ID");
        }

        departamentos.add(departamento);
    }

    public void modificarDepartamento(Departamento modificado){
        validarDepartamento(modificado);

        Departamento departamento = buscarPorId(modificado.getIdDepartamento());

        if (departamento == null){
            throw new IllegalArgumentException("El departamento no existe");
        }

        departamento.setNombre(departamento.getNombre());
    }

    public void eliminarDepartamento(String id){
        Departamento departamento = buscarPorId(id);

        if (departamento == null){
            throw new IllegalArgumentException("El departamento no existe");
        }

        departamentos.remove(departamento);
    }

    public ArrayList<Departamento> consultarDepartamentos(){
        return new ArrayList<>(departamentos);
    }

    public Departamento consultarDepartamento(String id){
        Departamento departamento = buscarPorId(id);

        if (departamento == null){
            throw new IllegalArgumentException("El departamento no existe");
        }

        return departamento;
    }

    private void validarDepartamento(Departamento departamento) {
        if (departamento == null){ throw new IllegalArgumentException("El espacio puede ser nulo"); }
        if (departamento.getIdDepartamento() == null || departamento.getIdDepartamento().trim().isEmpty()){ throw new IllegalArgumentException("El ID es obligatorio"); }
        if (departamento.getNombre() == null || departamento.getNombre().trim().isEmpty()){ throw new IllegalArgumentException("El nombre es obligatorio"); }
    }

    private Departamento buscarPorId(String id){
        for (Departamento departamento : departamentos){
            if (departamento.getIdDepartamento().equals(id)){
                return departamento;
            }
        }
        return null;
    }
}
