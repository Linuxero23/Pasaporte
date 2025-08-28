package co.poli.edu.actividad1.vista;
import java.util.*;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.repositorio.PasaporteRepositorio;
import co.poli.edu.actividad1.modelo.Titular;
import co.poli.edu.actividad1.modelo.Ciudad;
import co.poli.edu.actividad1.modelo.Pais;

public class Main  {
    private static PasaporteRepositorio repositorio = new PasaporteRepositorio();
    public static void main(String[] args) throws InterruptedException {
        List<Ciudad> lst_ciudades=new ArrayList<>();
        lst_ciudades.add(new Ciudad("1","Bogotá"));
        lst_ciudades.add(new Ciudad("2","Chiquinquira"));
        lst_ciudades.add(new Ciudad("3","Granada"));
        lst_ciudades.add(new Ciudad("4","Mariquita"));
        Pais Colombia=new Pais("1","Colombia",lst_ciudades);
        Titular marg=new Titular("1","Margaret Corasick","13/05/1945");
        Pasaporte pasaporte=new Pasaporte("1","27/08/2035",marg, Colombia);
        List<Pasaporte> s=seleccionarTodos();
        for(Pasaporte p:s)
            System.out.println(p);
    }
    static String insertar(Pasaporte pasaporte) {
        return repositorio.insertar(pasaporte);
    }
    static String eliminar(String id){
        return repositorio.eliminar(id);
    }
    static Pasaporte seleccionar(String id){
        return repositorio.seleccionar(id);
    }
    static List<Pasaporte> seleccionarTodos(){
        return repositorio.seleccionarTodos();
    }

}
