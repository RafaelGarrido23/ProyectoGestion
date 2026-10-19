package servicio;

import dao.ReservaDAO;
import modelo.Espacio;
import modelo.EstadoReserva;
import modelo.Profesor;
import modelo.Reserva;

import java.util.ArrayList;

public class GestionReservas {

    private final ReservaDAO reservaDAO;
    private final ArrayList<Reserva> reservas;

    public GestionReservas(ReservaDAO reservaDAO, ArrayList<Reserva> reservas) {
        this.reservaDAO = reservaDAO;
        this.reservas = reservaDAO.leerFichero();
    }

    public void crearReserva(Reserva reserva){
        validarReserva(reserva);

        if ((buscarPorId(reserva.getId()) != null)){
            throw new IllegalArgumentException("Ya existe una reserva con ese ID");
        }

        comprobarDisponibilidad(reserva);

        reservas.add(reserva);
        guardar();
    }

    public ArrayList<Reserva> consultarReservas(){
        return new ArrayList<>(reservas);
    }

    public Reserva consultarReserva(String id){
        Reserva reserva = buscarPorId(id);

        if (reserva == null){
            throw new IllegalArgumentException("La reserva no existe");
        }

        return reserva;
    }

    public ArrayList<Reserva> consultarPorProfesor(String id){
        ArrayList<Reserva> resultado = new ArrayList<>();

        for (Reserva reserva : reservas){
            if (reserva.getProfesor() != null &&
                    reserva.getProfesor().getId() != null &&
                    reserva.getProfesor().getId().equals(id)){
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public ArrayList<Reserva> consultarPorEspacio(String id){
        ArrayList<Reserva> resultado = new ArrayList<>();

        for (Reserva reserva : reservas){
            if (reserva.getEspacio() != null &&
                    reserva.getEspacio().getId() != null &&
                    reserva.getEspacio().getId().equals(id)){
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public void cancelarReserva(String id) {
        Reserva reserva = buscarPorId(id);

        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no existe");
        }

        if (reserva.getEstado() == EstadoReserva.Cancelada) {
            throw new IllegalArgumentException("La reserva ya está cancelada");
        }

        reserva.setEstado(EstadoReserva.Cancelada);
        guardar();
    }

    private void comprobarDisponibilidad(Reserva nuevo) {
        Espacio espacio = nuevo.getEspacio();
        Profesor profesor = nuevo.getProfesor();

        if (!espacio.isDisponible()) {
            throw new IllegalArgumentException("El espacio no está disponible");
        }

        if (nuevo.getNumAsistentes() > espacio.getCapacidad()) {
            throw new IllegalArgumentException(
                    "Se supera la capacidad del espacio");
        }

        for (Reserva existente : reservas) {

            // Las reservas canceladas no bloquean horarios
            if (existente.getEstado() ==
                    EstadoReserva.Cancelada) {
                continue;
            }

            // Comparan reservas del mismo día
            if (!existente.getFecha().equals(nuevo.getFecha())) {
                continue;
            }

            boolean mismoEspacio =
                    existente.getEspacio() != null
                            && existente.getEspacio().getId()
                            .equals(espacio.getId());

            boolean mismoProfesor =
                    existente.getProfesor() != null
                            && existente.getProfesor().getId()
                            .equals(profesor.getId());

            boolean seSolapan =
                    nuevo.getHoraInicio().isBefore(
                            existente.getHoraFin())
                            && existente.getHoraInicio().isBefore(
                            nuevo.getHoraFin());

            if (seSolapan && mismoEspacio) {
                throw new IllegalArgumentException("El espacio ya tiene una reserva " + "en ese horario");
            }

            if (seSolapan && mismoProfesor) {
                throw new IllegalArgumentException("El profesor ya tiene una reserva " + "en ese horario");
            }
        }
    }

    private void validarReserva(Reserva reserva) {
        if (reserva == null){ throw new IllegalArgumentException("La reserva puede ser nula"); }
        if (reserva.getId() == null || reserva.getId().trim().isEmpty()){ throw new IllegalArgumentException("El ID es obligatorio"); }
        if (reserva.getFecha() == null){ throw new IllegalArgumentException("La fecha es obligatoria"); }
        if (reserva.getHoraInicio() == null || reserva.getHoraFin() == null){ throw new IllegalArgumentException("Las horas son obligatorias"); }
        if (reserva.getHoraFin().isAfter(reserva.getHoraInicio())){ throw new IllegalArgumentException("La hora final debe ser posterior a la inicial"); }
        if (reserva.getProfesor() == null){ throw new IllegalArgumentException("Debe indicarse un profesor"); }
        if (reserva.getEspacio() == null){ throw new IllegalArgumentException("Debe indicarse un espacio"); }
        if (reserva.getNumAsistentes() <= 0){ throw new IllegalArgumentException("Debe haber al menos un asistente"); }
        if (reserva.getEstado() == null){ throw new IllegalArgumentException("Debe indicarse el estado de la reserva"); }
    }

    private Reserva buscarPorId(String id){
        for (Reserva reserva : reservas){
            if (reserva.getId().equals(id)){
                return reserva;
            }
        }
        return null;
    }

    private void guardar(){
        reservaDAO.escribirFichero(reservas);
    }

}
