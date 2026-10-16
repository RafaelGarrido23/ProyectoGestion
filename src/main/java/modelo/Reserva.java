package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Profesor profesor;
    private Espacio espacio;
    private int numAsistentes;
    private String motivo;
    private EstadoReserva estado;

    public Reserva() {
    }

    public Reserva(int id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Profesor profesor,
                   Espacio espacio, int numAsistentes, String motivo, EstadoReserva estado) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.profesor = profesor;
        this.espacio = espacio;
        this.numAsistentes = numAsistentes;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Getter & Setter "ID 'Reserva'"
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Getter & Setter "Fecha 'Reserva'"
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    // Getter & Setter "horaInicio 'Reserva'"
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    // Getter & Setter "horaFin 'Reserva'"
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    // Getter & Setter "Profesor 'Reserva'"
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    // Getter & Setter "Espacio 'Reserva'"
    public Espacio getEspacio() { return espacio; }
    public void setEspacio(Espacio espacio) { this.espacio = espacio; }

    // Getter & Setter "numAsistentes 'Reserva'"
    public int getNumAsistentes() { return numAsistentes; }
    public void setNumAsistentes(int numAsistentes) { this.numAsistentes = numAsistentes; }

    // Getter & Setter "Motivo 'Reserva'"
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    // Getter & Setter "Estado 'Reserva'"
    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "ID Reserva: " + id +
                " | Fecha: " + fecha +
                " | Hora Inicio: " + horaInicio +
                " | Hora Fin: " + horaFin +
                " | Profesor: " + profesor +
                " | Espacio: " + espacio +
                " | NºAsistentes: " + numAsistentes +
                " | Motivo: " + motivo +
                " | Estado: " + estado;
    }
}
