package modelo;

import java.io.Serializable;

public class Espacio implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String tipo;
    private int capacidad;
    private boolean disponible;

    public Espacio() {
    }

    public Espacio(String id, String nombre, String tipo,
                   int capacidad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    // Getter & Setter "ID 'Espacio'"
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    // Getter & Setter "Nombre 'Espacio'"
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Getter & Setter "Tipo 'Espacio'"
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    // Getter & Setter "Capacidad 'Espacio'"
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    // Getter & Setter "Disponibilidad 'Espacio'"
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "ID Espacio: " + id +
                " | Nombre: " + nombre +
                " | Tipo: " + tipo +
                " | Capacidad: " + capacidad +
                " | Disponibilidad: " + disponible;
    }
}
