package modelo;

import java.util.List;

public class Pais {
    private String codigo;
    private String nombre;
    private List<Ciudad> ciudades;

    public Pais(String codigo, String nombre, List<Ciudad> ciudades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ciudades = ciudades;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    @Override
    public String toString() {
        return nombre + " [" + codigo + "]";
    }
}
