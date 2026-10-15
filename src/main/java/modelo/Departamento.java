package modelo;

import java.io.Serializable;

public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;

    public Departamento() {
    }

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getter & Setter "ID 'Departamento'"
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Getter & Setter "Nombre 'Departamento'"
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "ID Departamento: " + id +
                " | Nombre: " + nombre;

    }
}
