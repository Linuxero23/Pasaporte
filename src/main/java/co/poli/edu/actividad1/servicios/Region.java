package co.poli.edu.actividad1.servicios;

import java.util.ArrayList;
import java.util.List;

public class Region implements EspacioGeografico {
    private String nombre;
    private EspacioGeografico padre;
    private List<EspacioGeografico> children = new ArrayList<>();

    public Region(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void add(EspacioGeografico e) {
        children.add(e);
    }

    public void remove(EspacioGeografico e) {
        children.remove(e);
    }

    public List<EspacioGeografico> getChildren() {
        return children;
    }

    @Override
    public List<EspacioGeografico> getArbolJerarquico() {
        List<EspacioGeografico> lista = new ArrayList<>();
        lista.add(this);
        for (EspacioGeografico child : children) {
            lista.addAll(child.getArbolJerarquico());
        }
        return lista;
    }

    public EspacioGeografico getPadre() {
        return padre;
    }

    public void setPadre(EspacioGeografico padre) {
        this.padre = padre;
    }
}
