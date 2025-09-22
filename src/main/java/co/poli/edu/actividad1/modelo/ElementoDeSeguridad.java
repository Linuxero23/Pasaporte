package co.poli.edu.actividad1.modelo;

public class ElementoDeSeguridad  {
    private String id;
    private String tipoDeElemento;
    private String descripcion;

    public ElementoDeSeguridad(String id, String tipoDeElemento, String descripcion) {
        this.id = id;
        this.tipoDeElemento = tipoDeElemento;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDeElemento() {
        return tipoDeElemento;
    }

    public void setTipoDeElemento(String tipoDeElemento) {
        this.tipoDeElemento = tipoDeElemento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "ElementoDeSeguridad{id='" + id + '\'' +
                ", tipoDeElemento='" + tipoDeElemento + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}