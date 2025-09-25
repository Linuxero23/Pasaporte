package co.poli.edu.actividad1.vista;

import java.util.*;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.*;
import co.poli.edu.actividad1.servicios.*;

public class Main  {
    private static PasaporteRepositorio repositorio = new PasaporteRepositorio();
    public static void main(String[] args) throws InterruptedException {
        Region pais = new Region("Colombia");
        Region region1 = new Region("Andina");
        Ciudad bogota = new Ciudad("101", "Bogotá");
        CiudadLeaf bogotaAdaptada = new AdaptadorCiudad(bogota);
        region1.add(bogotaAdaptada);
        pais.add(region1);
        for (EspacioGeografico e : pais.getArbolJerarquico()) {
            System.out.println(e.getNombre());
        }
    }
    static String insertar(Pasaporte pasaporte) {return repositorio.insertar(pasaporte);}
    static String actualizar(String codigo,Pasaporte pasaporte) {return repositorio.actualizar(codigo,pasaporte);}
    static String eliminar(String id){return repositorio.eliminar(id);}
    static Pasaporte seleccionar(String id){return repositorio.seleccionar(id);}
    static List<Pasaporte> seleccionarTodos(){return repositorio.seleccionarTodos();}
    static List<Pasaporte> seleccionarConCaracter(char ch){return repositorio.seleccionarConCaracter(ch);}

}




