package modelo;

public class Titular {
    private String id;
    private String nombre;
    private String fechaNacimiento;

    public Titular(String id, String nombre, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ", Nacimiento: " + fechaNacimiento + ")";
    }
}
