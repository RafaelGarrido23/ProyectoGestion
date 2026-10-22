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

        if ((buscarPorId(reserva.getIdReserva()) != null)){
            throw new IllegalArgumentException("Ya existe una reserva con ese ID");
        }

        comprobarDisponibilidad(reserva);

        reservas.add(reserva);
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

    public ArrayList<Reserva> consultarPorProfesor(Profesor id){
        ArrayList<Reserva> resultado = new ArrayList<>();

        for (Reserva reserva : reservas){
            if (reserva.getIdProfesor() != null &&
                    reserva.getIdProfesor().equals(id)){
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public ArrayList<Reserva> consultarPorEspacio(Espacio id){
        ArrayList<Reserva> resultado = new ArrayList<>();

        for (Reserva reserva : reservas){
            if (reserva.getIdEspacio() != null &&
                    reserva.getIdEspacio().equals(id)){
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
    }

    private void comprobarDisponibilidad(Reserva nuevo) {
        Espacio espacio = nuevo.getIdEspacio();
        Profesor profesor = nuevo.getIdProfesor();

        if (nuevo.getEstado().equals(EstadoReserva.Confirmada)) {
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
                    existente.getIdEspacio() != null
                            && existente.getIdEspacio().getIdEspacio()
                            .equals(espacio.getIdEspacio());

            boolean mismoProfesor =
                    existente.getIdProfesor() != null
                            && existente.getIdProfesor().getIdProfesor()
                            .equals(profesor.getIdProfesor());

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
        if (reserva.getIdReserva() == null || reserva.getIdReserva().trim().isEmpty()){ throw new IllegalArgumentException("El ID es obligatorio"); }
        if (reserva.getFecha() == null){ throw new IllegalArgumentException("La fecha es obligatoria"); }
        if (reserva.getHoraInicio() == null || reserva.getHoraFin() == null){ throw new IllegalArgumentException("Las horas son obligatorias"); }
        if (reserva.getHoraFin().isAfter(reserva.getHoraInicio())){ throw new IllegalArgumentException("La hora final debe ser posterior a la inicial"); }
        if (reserva.getIdProfesor() == null){ throw new IllegalArgumentException("Debe indicarse un profesor"); }
        if (reserva.getIdEspacio() == null){ throw new IllegalArgumentException("Debe indicarse un espacio"); }
        if (reserva.getNumAsistentes() <= 0){ throw new IllegalArgumentException("Debe haber al menos un asistente"); }
        if (reserva.getEstado() == null){ throw new IllegalArgumentException("Debe indicarse el estado de la reserva"); }
    }

    private Reserva buscarPorId(String id){
        for (Reserva reserva : reservas){
            if (reserva.getIdReserva().equals(id)){
                return reserva;
            }
        }
        return null;
    }
}
