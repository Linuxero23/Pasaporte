package co.poli.edu.actividad1.servicios;

import java.util.ArrayList;
import java.util.List;

public class CiudadL implements EspacioGeografico {
    private String nombre;
    private EspacioGeografico padre;

    public CiudadL(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public List<EspacioGeografico> getArbolJerarquico() {
        List<EspacioGeografico> lista = new ArrayList<>();
        lista.add(this);
        return lista;
    }

    public EspacioGeografico getPadre() {
        return padre;
    }

    public void setPadre(EspacioGeografico padre) {
        this.padre = padre;
    }
}
