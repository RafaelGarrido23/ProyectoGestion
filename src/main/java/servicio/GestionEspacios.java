package servicio;

import dao.EspacioDAO;
import modelo.Espacio;

import java.util.ArrayList;

public class GestionEspacios {

    private final EspacioDAO espacioDAO;
    private final ArrayList<Espacio> espacios;

    public GestionEspacios(EspacioDAO espacioDAO, ArrayList<Espacio> espacios) {
        this.espacioDAO = espacioDAO;
        this.espacios = espacioDAO.leerFichero();
    }

    public void altaEspacio(Espacio espacio){
            validarEspacio(espacio);

            if ((buscarPorId(espacio.getIdEspacio()) != null)){
                throw new IllegalArgumentException("Ya existe un espacio con ese ID");
            }

            espacios.add(espacio);
    }

    public void modificarEspacio(Espacio modificado){
        validarEspacio(modificado);

        Espacio espacio = buscarPorId(modificado.getIdEspacio());

        if (espacio == null){
            throw new IllegalArgumentException("El espacio no existe");
        }

        espacio.setNombre(espacio.getNombre());
        espacio.setTipo(espacio.getTipo());
        espacio.setCapacidad(espacio.getCapacidad());
        espacio.setTipo(espacio.getTipo());
    }

    public void eliminarEspacio(String id){
        Espacio espacio = buscarPorId(id);

        if (espacio == null){
            throw new IllegalArgumentException("El espacio no existe");
        }

        espacios.remove(espacio);
    }

    public ArrayList<Espacio> consultarEspacios(){
        return new ArrayList<>(espacios);
    }

    public Espacio consultarEspacio(String id){
        Espacio espacio = buscarPorId(id);

        if (espacio == null){
            throw new IllegalArgumentException("El espacio no existe");
        }

        return espacio;
    }

    public void cambiarDisponibilidad(String id, boolean disponible){
        Espacio espacio = buscarPorId(id);

        if (espacio == null){
            throw new IllegalArgumentException("El espacio no existe");
        }

        espacio.setDisponible(disponible);
    }

    private void validarEspacio(Espacio espacio) {
        if (espacio == null){ throw new IllegalArgumentException("El espacio puede ser nulo"); }
        if (espacio.getIdEspacio() == null || espacio.getIdEspacio().trim().isEmpty()){ throw new IllegalArgumentException("El ID es obligatorio"); }
        if (espacio.getNombre() == null || espacio.getNombre().trim().isEmpty()){ throw new IllegalArgumentException("El nombre es obligatorio"); }
        if (espacio.getTipo() == null || espacio.getTipo().trim().isEmpty()){ throw new IllegalArgumentException("El tipo es obligatorio"); }
        if (espacio.getCapacidad() <= 0){ throw new IllegalArgumentException("La capacidad debe ser mayor que 0"); }

    }

    private Espacio buscarPorId(String id){
        for (Espacio espacio : espacios){
            if (espacio.getIdEspacio().equals(id)){
                return espacio;
            }
        }
        return null;
    }
}
