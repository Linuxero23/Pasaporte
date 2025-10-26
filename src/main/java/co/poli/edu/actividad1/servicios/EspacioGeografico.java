package co.poli.edu.actividad1.servicios;

import java.util.*;

public abstract class EspacioGeografico {
    protected EspacioGeografico padre;
    private String nombre;
    public String getNombre(){
        return nombre;
    }
    protected void setNombre(String nombre){
        this.nombre = nombre;
    }
    public EspacioGeografico getPadre() {
        return padre;
    }
    public List<EspacioGeografico> getArbolJerarquico(){
        LinkedList<EspacioGeografico> lst=new LinkedList<>();
        helper(lst,padre);
        lst.add(this);
        return lst;
    }
    private void helper(LinkedList<EspacioGeografico>lst,EspacioGeografico root){
        if(root==null)return;
        lst.addFirst(root);
        helper(lst,root.padre);
    }
    public String toString(){
        return nombre;
    }
}
