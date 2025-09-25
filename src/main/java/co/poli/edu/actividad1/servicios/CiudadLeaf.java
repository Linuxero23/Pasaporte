package co.poli.edu.actividad1.servicios;

import java.util.ArrayList;
import java.util.List;

public class CiudadLeaf extends EspacioGeografico {
    public CiudadLeaf(String nombre, EspacioGeografico padre) {
        this.padre=(padre);
        this.setNombre(nombre);
    }
    public CiudadLeaf(String nombre) {
        this.setNombre(nombre);
    }
}
