package modelo;

import java.io.Serializable;

public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String email;
    private Departamento departamento;

    public Profesor() {
    }

    public Profesor(String id, String nombre, String email,
                    Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
    }

    // Getter & Setter "ID 'Profesor'"
    public String getIdProfesor() { return id; }
    public void setIdProfesor(String id) { this.id = id; }

    // Getter & Setter "Nombre 'Profesor'"
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    // Getter & Setter "Email 'Profesor'"
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Getter & Setter "Departamento 'Profesor'"
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    @Override
    public String toString() {
        return "ID Profesor: " + id +
                " | Nombre: " + nombre +
                " | Email: " + email +
                " | Departamento: " + departamento;
    }
}
