package co.poli.edu.actividad1.servicios;

import java.util.*;

public abstract class EspacioGeografico {
    protected EspacioGeografico padre;
    private String nombre;
    public String getNombre(){
        return nombre;
    }
    public List<EspacioGeografico> getArbolJerarquico(){
        List<EspacioGeografico> lst=new LinkedList<>();
        helper(lst,padre);
        return lst;
    }
    private void helper(List<EspacioGeografico>lst,EspacioGeografico root){
        if(root==null)return;
        lst.addFirst(root);
        helper(lst,root.padre);
    }
}
