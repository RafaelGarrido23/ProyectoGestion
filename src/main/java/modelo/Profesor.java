package modelo;

import java.io.Serializable;

public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String apellidos;
    private String email;
    private Departamento idDepartamento;

    public Profesor() {
    }

    public Profesor(String id, String nombre, String apellidos, String email, Departamento idDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.idDepartamento = idDepartamento;
    }

    // Getter & Setter "ID 'Profesor'"
    public String getIdProfesor() { return id; }
    public void setIdProfesor(String id) { this.id = id; }

    // Getter & Setter "Nombre 'Profesor'"
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = apellidos; }

    // Getter & Setter "Apellidos 'Profesor'"
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    // Getter & Setter "Email 'Profesor'"
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Getter & Setter "Departamento 'Profesor'"
    public Departamento getIdDepartamento() { return idDepartamento; }
    public void setIdDepartamento(Departamento idDepartamento) { this.idDepartamento = idDepartamento; }

    @Override
    public String toString() {
        return "ID Profesor: " + id +
                " | Nombre: " + nombre +
                " | Apellidos: " + apellidos +
                " | Email: " + email +
                " | Departamento: " + idDepartamento;
    }
}
