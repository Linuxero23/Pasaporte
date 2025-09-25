package co.poli.edu.actividad1.servicios;

import java.util.ArrayList;
import java.util.List;

public class CiudadLeaf extends EspacioGeografico {
    private String nombre;
    private EspacioGeografico padre;
    public CiudadLeaf(String nombre, EspacioGeografico padre) {
        this.nombre = nombre;
        this.padre = padre;
    }
    public CiudadLeaf(String nombre) {
        this.nombre = nombre;
    }
    public EspacioGeografico getPadre() {
        return padre;
    }
}
