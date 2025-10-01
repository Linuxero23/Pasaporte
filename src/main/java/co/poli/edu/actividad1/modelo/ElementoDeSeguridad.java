package co.poli.edu.actividad1.modelo;

public abstract class ElementoDeSeguridad  {
    private String id;
    private String descripcion;

    // Getters y Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Elemento de seguriadad-> Id="+id+", Descripcion="+descripcion;
    }
}