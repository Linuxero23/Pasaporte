package co.poli.edu.actividad1.servicios;
import java.util.*;
public class Region extends EspacioGeografico {
    private List<EspacioGeografico> children;
    public Region(String nombre) {
        this.setNombre(nombre);
        children=new ArrayList<>();
    }
    public Region(String nombre, EspacioGeografico padre){
        this.padre=(padre);
        this.setNombre(nombre);
    }
    public List<EspacioGeografico> getChildren(){
        return children;
    }
    public void add(EspacioGeografico hijo){
        children.add(hijo);
        hijo.padre= this;
    }
    public void remove(EspacioGeografico hijo){
        hijo.padre= null;
        children.remove(hijo);
    }
}
